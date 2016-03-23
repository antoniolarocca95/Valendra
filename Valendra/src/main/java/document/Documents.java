package document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parser.ParserPDF;

public class Documents extends HttpServlet {

	/*
	 * Static variables to keep track of document types
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int TYPE_HTML = 0;
	static final int TYPE_TXT = 1;
	static final int TYPE_PDF = 2;

	/**
	 * All variables needed for our documents
	 */

	int docType;
	int rating;
	String uploader;
	String[] tags;
	String[] comments;
	String FILES_DIRECTORY;

	public void init() {
		// Get the file location where it would be stored.
		FILES_DIRECTORY = getServletContext().getInitParameter("file-upload");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		String docName = request.getParameter("document");
		if (docName.substring(docName.length() - 3, docName.length()).equals("pdf")) {
			String[] text = ParserPDF.parse(FILES_DIRECTORY + docName);
			for (String line : text) {
				out.print(line + "<br />");
			}
		} else {
			FileReader fr = new FileReader(FILES_DIRECTORY + docName);
			BufferedReader br = new BufferedReader(fr);
			String line;
			out.println("<xmp>");
			while ((line = br.readLine()) != null) {
				out.println(line + "<br />");
			}
			out.println("</xmp>");
			br.close();
		}
	}
}
