package org.valendra.searchengine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import org.valendra.accounts.AccountsLogin;
import org.valendra.valendra.Header;

/*Search Engine for Valendra
 *
 *This class is responsible for taking the parsed user input
 *and passing it into the database handler to receive
 *a list of related documents.
 *
 *This will output a list of documents to be displaeyed on the front end.
 */
public class SearchEngine extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String FILES_DIRECTORY;

	public void init() {
		// Get the file location where it would be stored.
		FILES_DIRECTORY = getServletContext().getInitParameter("file-upload");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		Header.drawHeader(out);
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"upload.css\">"); 
		out.println("<form action=\"search\" method=\"post\">");
		out.println("<br />");
		out.println("<input type=\"text\" name=\"search\" placeholder=\"Search\"/>");
		out.println("<br />");
		out.println("<input type=\"submit\" value=\"Search\" />");
		out.println("</form>");
		String searchString = request.getParameter("search");
		if (AccountsLogin.LOGGED_IN.equals("false")) {
			out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
		} else {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Results</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"search.css\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"results\">");

			StandardAnalyzer analyzer = new StandardAnalyzer();
			Directory index = new RAMDirectory();
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			IndexWriter w = new IndexWriter(index, config);
			File dir = new File(FILES_DIRECTORY);
			File[] files = dir.listFiles();
			for (File file : files) {
				Document document = new Document();
				String name = file.getName();

				if (name.substring(name.length() - 3).equals("pdf")) {
					String contents = Arrays.toString(ParserPDF.parse(FILES_DIRECTORY + name));
					document.add(new TextField("contents", contents, Field.Store.YES));
				} else {
					Reader reader = new FileReader(file);
					document.add(new TextField("contents", reader));
				}
				document.add(new TextField("name", name, Field.Store.YES));

				w.addDocument(document);
			}

			Query q1 = null;
			Query q2 = null;
			try {
				q1 = new QueryParser("name", analyzer).parse(searchString);
				q2 = new QueryParser("contents", analyzer).parse(searchString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int hitsPerPage = 10;
			IndexReader reader = DirectoryReader.open(w);
			IndexSearcher searcher = new IndexSearcher(reader);
			TopDocs docs1 = searcher.search(q1, hitsPerPage);
			TopDocs docs2 = searcher.search(q2, hitsPerPage);
			ScoreDoc[] hits = Arrays.copyOf(docs1.scoreDocs, docs1.scoreDocs.length + docs2.scoreDocs.length);
			System.arraycopy(docs2.scoreDocs, 0, hits, docs1.scoreDocs.length, docs2.scoreDocs.length);

			out.println("Found " + hits.length + " hits.<br>");
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				out.println("<a href=\"result?document=" + d.get("name") + "\">" + d.get("name") + "</a><br />");
			}
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			w.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}