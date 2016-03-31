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

			Header.drawHeader(out);
			
			out.println("<h1>UTM Search Engine</h1>");
			out.println("<div style=\"width:400px; position:relative; top:200px; margin-right:auto; margin-left:auto; border:1px hidden #000;\">");
			out.println("<form action=\"search\" method=\"post\">");
			out.println("<br />");
			out.println("<input type=\"text\" name=\"search\" placeholder=\"Search\"/>");
			out.println("<br />");
			out.println("<input type=\"submit\" value=\"Search\" />");
			out.println("</form>");
			out.println("</div>");
			/*
			out.println("<form action=\"home\" method=\"post\">");
			out.println("<input type=\"submit\" value=\"Logout\" />");
			out.println("</form>");
			*/
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