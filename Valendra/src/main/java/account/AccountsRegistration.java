package account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseHandler;

public class AccountsRegistration extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void addUser(String firstname, String lastname, String email, String username, String password)
			throws Exception {
		Connection c = DatabaseHandler.connectToDatabase();
		c.setAutoCommit(false);
		Statement stment = c.createStatement();
		String sql = "insert into tab_acc " + "values (\"" + firstname + "\", \"" + lastname + "\", \"" + email
				+ "\", \"" + username + "\", \"" + password + "\");";
		stment.executeUpdate(sql);
		stment.close();
		c.commit();
		c.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Register Form</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"register\" method=\"post\">");
		out.println("<br />");
		out.println("<input type=\"text\" name=\"firstname\" placeholder=\"First Name\"/>");
		out.println("<br />");
		out.println("<input type=\"text\" name=\"lastname\" placeholder=\"Last Name\"/>");
		out.println("<br />");
		out.println("<input type=\"text\" name=\"email\" placeholder=\"Email\"/>");
		out.println("<br />");
		out.println("<input type=\"text\" name=\"username\" placeholder=\"Username\"/>");
		out.println("<br />");
		out.println("<input type=\"password\" name=\"password\" placeholder=\"Password\"/>");
		out.println("<br />");
		out.println("<input type=\"password\" name=\"cpassword\" placeholder=\"Confirm Password\"/>");
		out.println("<br />");
		out.println("<input type=\"submit\" value=\"Register\" />");
		out.println("</form>");
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

		if (cpassword.equals(password)) {
			try {
				String hash = Password.hash(password);
				try {
					addUser(firstname, lastname, email, username, hash);
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
