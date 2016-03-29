package org.valendra.valendra;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.valendra.accounts.AccountsLogin;

public class Valendra extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		if (AccountsLogin.LOGGED_IN.equals("false")) {
			out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
		} else {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Valendra</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"home.css\">");
			out.println("</head>");
			out.println("<body>");

			out.println("<ul>");
			out.println("<li><a href=\"#Logout\">Logout</a></li>");
			out.println("<li><a href=\"Find\">Buddy</a></li>");
			out.println("<li><a href=\"#Upload\">Upload</a></li>");
			out.println("<li><a href=\"#AccountInfo\">Account</a></li>");
			out.println("</ul>");
			out.println("<div class=\"dropdown\">");
			out.println("<button class=\"dropbtn\" id=\"star\"></button>");
			out.println("<div class=\"dropdown-content\">");
			out.println("</div>");
			out.println("</div>");

			out.println("<h3>Welcome</h3>");
			out.println("Select a file to upload: <br />");
			out.println("<form action=\"upload\" method=\"post\" enctype=\"multipart/form-data\">");
			out.println("<input type=\"file\" name=\"file\" size=\"50\" />");
			out.println("<br />");
			out.println("<input type=\"submit\" value=\"Upload File\" />");
			out.println("</form>");
			out.println("<form action=\"search\" method=\"post\">");
			out.println("<br />");
			out.println("<input type=\"text\" name=\"search\" placeholder=\"Search\"/>");
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
		AccountsLogin.LOGGED_IN = "false";
		out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
		out.println("<script>function myFunction() {alert(\"You have been logged out\")}; myFunction();</script>");
	}
}