package org.valendra.accounts;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.valendra.database.DatabaseHandler;
import org.valendra.valendra.Header;

public class Buddy extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		Header.drawHeader(out);
		out.println("<h1>Find a study buddy</h1>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"home.css\">");
		out.println("<form action=\"buddy\" method=\"post\">");
		out.println("<input type=\"radio\" name=\"buddy\" value=\"firstname\"> First Name<br>");
		out.println("<input type=\"radio\" name=\"buddy\" value=\"lastname\"> Last Name<br>");
		out.println("<input type=\"radio\" name=\"buddy\" value=\"email\"> Email Address<br>");
		out.println("<input type=\"radio\" name=\"buddy\" value=\"username\"> Username<br>");
		out.println("<input type=\"text\" name=\"search\" placeholder=\"Search\"><br>");
		out.println("<input type=\"submit\" value=\"Find Buddy\" />");
		out.println("<br />");
		out.println("</form>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		String buddyRadio = request.getParameter("buddy");
		String input = request.getParameter("search");
		Header.drawHeader(out);
		out.println("<h1>Study buddies</h1>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"home.css\">");
		String parameter = "";

		if (buddyRadio.equalsIgnoreCase("firstname")) {
			parameter = "firstname";
		}

		else if (buddyRadio.equalsIgnoreCase("lastname")) {
			parameter = "lastname";
		}

		else if (buddyRadio.equalsIgnoreCase("email")) {
			parameter = "email";
		}

		else if (buddyRadio.equalsIgnoreCase("username")) {
			parameter = "username";
		}

		if (parameter.equalsIgnoreCase("")) {
			out.println("<script>alert(\"Select an option!\");</script>");
			out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/buddy\" />");
		}

		if (input.equalsIgnoreCase("")) {
			out.println("<script>alert(\"Missing input!\");</script>");
			out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/buddy\" />");
		}

		else if (!input.equalsIgnoreCase("")) {
			ArrayList<String> buddies = DatabaseHandler.findBuddy(parameter, input);
			for (String buddy : buddies) {
				out.println(buddy + " <br>");
			}

		}

	}
}
