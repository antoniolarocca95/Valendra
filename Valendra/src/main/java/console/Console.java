package console;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.Accounts;

public class Console extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		if (Accounts.LOGGED_IN.equals("false")) {
			out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
		} else {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>File Uploading Form</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>File Upload:</h3>");
			out.println("Select a file to upload: <br />");
			out.println("<form action=\"upload\" method=\"post\" enctype=\"multipart/form-data\">");
			out.println("<input type=\"file\" name=\"file\" size=\"50\" />");
			out.println("<br />");
			out.println("<input type=\"submit\" value=\"Upload File\" />");
			out.println("</form>");
			out.println("<form action=\"search\" method=\"post\">");
			out.println("<br />");
			out.println("<input type=\"text\" name=\"search\" placeholder=\"search\"/>");
			out.println("<br />");
			out.println("<input type=\"submit\" value=\"Search\" />");
			out.println("</form>");
			out.println("<form action=\"home\" method=\"post\">");
			out.println("<input type=\"submit\" value=\"Logout\" />");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		Accounts.LOGGED_IN = "false";
		out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
		out.println("<script>function myFunction() {alert(\"You have been logged out\")}; myFunction();</script>");
	}
}
