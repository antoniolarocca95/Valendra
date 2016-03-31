package org.valendra.accounts;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.valendra.database.*;

public class AccountsRegistration extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Register</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"register.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>UTM Search Engine</h1>");
		out.println(
				"<div style=\"width:400px; position:relative; top:200px; margin-right:auto; margin-left:auto; border:1px hidden #000;\">");
		out.println("<form action=\"register\" method=\"post\">");
		out.println("<br />");
		out.println("<input type=\"text\" name=\"firstname\" placeholder=\"First Name\"/>");
		out.println("<br />");
		out.println("<input type=\"text\" name=\"lastname\" placeholder=\"Last Name\"/>");
		out.println("<br />");
		out.println("<input type=\"email\" name=\"email\" placeholder=\"Email\"/>");
		out.println("<br />");
		out.println("<input type=\"text\" name=\"username\" placeholder=\"Username\"/>");
		out.println("<br />");
		out.println("<input type=\"password\" name=\"password\" placeholder=\"Password\"/>");
		out.println("<br />");
		out.println("<input type=\"password\" name=\"cpassword\" placeholder=\"Confirm Password\"/>");
		out.println("<br />");
		out.println("<input type=\"submit\" value=\"Register\" />");
		out.println("</form>");
		out.println("<form action=\"login\" method=\"get\">");
		out.println("<input type=\"submit\" value=\"Cancel\" />");
		out.println("</form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");

		if (firstname.equalsIgnoreCase("") || lastname.equalsIgnoreCase("") || email.equalsIgnoreCase("")
				|| username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
			out.println(
					"<script>function myFunction() {alert(\"No field can be left blank\")}; myFunction();</script>");
			out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/register\" />");
		}

		if (cpassword.equals(password)) {
			try {
				String hash = Password.hash(password);
				try {
					DatabaseHandler.addUser(firstname, lastname, email, username, hash);
					out.println(
							"<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
				} catch (Exception e) {
					out.println(e.getMessage());
				}
			} catch (Exception e) {
				out.println(
						"<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/register\" />");
				out.println("<script>function myFunction() {alert(\"Invalid Password\")}; myFunction();</script>");
			}

		} else {
			out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/register\" />");
			out.println("<script>function myFunction() {alert(\"Invalid Password\")}; myFunction();</script>");
		}
	}
}