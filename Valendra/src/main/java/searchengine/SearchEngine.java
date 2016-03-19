package searchengine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

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

/*Search Engine for Valendra
 *
 *This class is responsible for taking the parsed user input
 *and passing it into the database handler to receive
 *a list of related documents.
 *
 *This will output a list of documents to be displaeyed on the front end.
 */
public class SearchEngine {
	public static final String FILES_DIRECTORY = "filesDirectory";

	public static void search(String searchString) throws IOException, ParseException {
		StandardAnalyzer analyzer = new StandardAnalyzer();
		Directory index = new RAMDirectory();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter w = new IndexWriter(index, config);
		File dir = new File(FILES_DIRECTORY);
		File[] files = dir.listFiles();
		for (File file : files) {
			Document document = new Document();

			String name = file.getName();
			
			document.add(new TextField("name", name, Field.Store.YES));

			Reader reader = new FileReader(file);
			document.add(new TextField("contents", reader));

			w.addDocument(document);
		}
		psearch(searchString, w);
		w.close();
	}

	private static void psearch(String searchString, IndexWriter index) throws IOException, ParseException {
		StandardAnalyzer analyzer = new StandardAnalyzer();
		Query q1 = new QueryParser("name", analyzer).parse(searchString);
		Query q2 = new QueryParser("contents", analyzer).parse(searchString);

		int hitsPerPage = 10;
		IndexReader reader = DirectoryReader.open(index);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs docs1 = searcher.search(q1, hitsPerPage);
		TopDocs docs2 = searcher.search(q2, hitsPerPage);
		ScoreDoc[] hits = Arrays.copyOf(docs1.scoreDocs, docs1.scoreDocs.length + docs2.scoreDocs.length);
		System.arraycopy(docs2.scoreDocs, 0, hits, docs1.scoreDocs.length, docs2.scoreDocs.length);

		displayResults(hits, searcher);
	}

	private static void displayResults(ScoreDoc[] hits, IndexSearcher searcher) throws IOException {
		System.out.println("Found " + hits.length + " hits.");

		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println((i + 1) + ". " + "\t" + d.get("name"));
		}
	}
}
