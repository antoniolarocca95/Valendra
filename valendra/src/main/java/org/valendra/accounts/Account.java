package org.valendra.accounts;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.valendra.database.DatabaseHandler;
import org.valendra.valendra.Header;

public class Account extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		Header.drawHeader(out);
		out.println("<h1>Account Information</h1>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"account.css\">");
		ArrayList<String> accountInfo = DatabaseHandler.getAccountInformation(AccountsLogin.LOGGED_IN);
		out.println(
				"<div style=\"width:400px; position:relative; top:150px; margin-right:auto; margin-left:auto; border:1px hidden #000;\">");
		out.println("<form action=\"account\" method=\"post\">");
		out.println("<p class=\"paragraph\"> Username: " + accountInfo.get(3) + "</p>");
		out.println("<p class=\"paragraph\"> First name: " + accountInfo.get(0)
				+ "</p><input type=\"text\" name=\"newfirstname\" placeholder=\"New First Name\"><br>");
		out.println("<p class=\"paragraph\"> Last name: " + accountInfo.get(1)
				+ "</p><input type=\"text\" name=\"newlastname\" placeholder=\"New Last Name\"><br>");
		out.println("<p class=\"paragraph\"> Email address: " + accountInfo.get(2)
				+ "</p><input type=\"email\" name=\"newemail\" placeholder=\"New Email Address\"><br>");
		out.println(
				"<p class=\"paragraph\"> Current password</p> <input type=\"password\" name=\"currentpassword\" placeholder=\"Current Password\"><br>");
		out.println(
				"<p class=\"paragraph\"> New password</p> <input type=\"password\" name=\"newpassword\" placeholder=\"New Password\"><br>");
		out.println(
				"<p class=\"paragraph\"> Confirm new password</p> <input type=\"password\" name=\"confirmpassword\" placeholder=\"Confirm New Password\"><br>");
		out.println("<br />");
		out.println("<input type=\"submit\" value=\"Change\" />");
		out.println("<br />");
		out.println("</form>");
		out.println("</div>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		String fName = request.getParameter("newfirstname");
		String lName = request.getParameter("newlastname");
		String email = request.getParameter("newemail");
		String currentPass = request.getParameter("currentpassword");
		String newPass = request.getParameter("newpassword");
		String confirmPass = request.getParameter("confirmpassword");

		ArrayList<String> accountInfo = DatabaseHandler.getAccountInformation(AccountsLogin.LOGGED_IN);
		String currentUserPassword = accountInfo.get(4);
		// if any three of them are null, then auto decline
		// if theyre all filled in, then check first if they correctly entered
		// their password

		// if all three are empty, then we know they dont wanna change their
		// password
		if (currentPass.equalsIgnoreCase("") && newPass.equalsIgnoreCase("") && confirmPass.equalsIgnoreCase("")) {

			if (fName.equalsIgnoreCase("")) {
				fName = accountInfo.get(0);
			}

			if (lName.equalsIgnoreCase("")) {
				lName = accountInfo.get(1);
			}

			if (email.equalsIgnoreCase("")) {
				email = accountInfo.get(2);
			}
			DatabaseHandler.deleteUser(AccountsLogin.LOGGED_IN);
			DatabaseHandler.addUser(fName, lName, email, AccountsLogin.LOGGED_IN, currentUserPassword);
			out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/account\" />");

		}

		// if at least one is empty, then we know they tried to change their
		// pass, but missed something
		else if (currentPass.equalsIgnoreCase("") || newPass.equalsIgnoreCase("") || confirmPass.equalsIgnoreCase("")) {
			out.println("<script>alert(\"Missing some password fields!\");</script>");
			out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/account\" />");
		}

		// if all three are full, then we know they wanna change their password,
		// then do some error checking
		else if (!currentPass.equalsIgnoreCase("") && !newPass.equalsIgnoreCase("")
				&& !confirmPass.equalsIgnoreCase("")) {
			// check if the old password is correct and if the new password is
			// the confirm pass
			if (Password.hash(currentPass).equalsIgnoreCase(currentUserPassword)
					&& (newPass.equalsIgnoreCase(confirmPass))) {
				if (fName.equalsIgnoreCase("")) {
					fName = accountInfo.get(0);
				}

				if (lName.equalsIgnoreCase("")) {
					lName = accountInfo.get(1);
				}

				if (email.equalsIgnoreCase("")) {
					email = accountInfo.get(2);
				}
				DatabaseHandler.deleteUser(AccountsLogin.LOGGED_IN);
				DatabaseHandler.addUser(fName, lName, email, AccountsLogin.LOGGED_IN, Password.hash(newPass));
				out.println(
						"<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/account\" />");
			} else {
				out.println("<script>alert(\"Invalid password or passwords dont match!\");</script>");
				out.println(
						"<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/account\" />");
			}

		}

	}

}
