package account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseHandler;

public class Accounts extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean LOGGED_IN = false;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<form action=\"login\" method=\"post\" />");
		out.println("<input type=\"text\" name=\"username\" />");
		out.println("<br />");
		out.println("<input type=\"password\" name=\"password\" />");
		out.println("<br />");
		out.println("<input type=\"submit\" value=\"Login\" />");
		out.println("</form>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (DatabaseHandler.loginUser(username, password)) {
			out.println("<meta http-equiv=\"refresh\" content=\"0 url=http://localhost:8080/Valendra/home\" />");
			LOGGED_IN = true;
		} else {
			out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
			out.println(
					"<script>function myFunction() {alert(\"Invalid username or password\")}; myFunction();</script>");
		}
	}
}