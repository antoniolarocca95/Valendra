package org.valendra.accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.valendra.database.*;

public class AccountsLogin extends HttpServlet {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public static String LOGGED_IN = "false";

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Login</title>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"login.css\">");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Valendra</h1>");
    out.println(
        "<div style=\"width:400px; position:relative; top:200px; margin-right:auto; margin-left:auto; border:1px hidden #000;\">");
    out.println("<form action=\"login\" method=\"post\" />");
    out.println("<header>Login</header>");
    out.println("<label for=\"username\">Username </label>");
    out.println("<input type=\"text\" name=\"username\" />");
    out.println("<br />");
    out.println("<label id=\"ps\" for=\"password\">Password </label>");
    out.println("<input id=\"ps\" type=\"password\" name=\"password\" />");
    out.println("<br />");
    out.println("<input type=\"submit\" value=\"Login\" />");
    out.println("</form>");
    // out.println("</div>");
    // out.println("<div style=\"width:400px; margin-right:auto; margin-left:auto; border:1px solid
    // #000;\">");
    out.println("<form action=\"register\" method=\"get\" />");
    out.println("<br />");
    out.println("<input type=\"submit\" value=\"Register\" />");
    out.println("</form>");
    out.println("</div>");
    out.println("</body>");
    out.println("</html>");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    try {
      password = Password.hash(password);
      if (DatabaseHandler.loginUser(username, password)) {
        out.println(
            "<meta http-equiv=\"refresh\" content=\"0 url=/Valendra/home\" />");
        LOGGED_IN = username;
      } else {
        out.println(
            "<meta http-equiv=\"refresh\" content=\"0; url=/Valendra/login\" />");
        out.println(
            "<script>function myFunction() {alert(\"Invalid username or password\")}; myFunction();</script>");
      }
    } catch (Exception e) {
      out.println(
          "<meta http-equiv=\"refresh\" content=\"0; url=/Valendra/login\" />");
      out.println(
          "<script>function myFunction() {alert(\"Invalid username or password\")}; myFunction();</script>");
    }

  }
}
