package org.valendra.accounts;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.valendra.database.DatabaseHandler;
import org.valendra.valendra.Header;

public class BuddyAccount extends HttpServlet {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    if (AccountsLogin.LOGGED_IN.equals("false")) {
      out.println(
          "<meta http-equiv=\"refresh\" content=\"0; url=/Valendra/login\" />");
    } else {
      Header.drawHeader(out);
      out.println("<head>");
      out.println("<title>Valendra</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"account.css\">");
      out.println("</head>");
      String user = request.getParameter("user");

      out.println("<h1>Account Information</h1>");

      ArrayList<String> accountInfo = DatabaseHandler.getAccountInformation(user);

      out.println(
          "<div style=\"width:400px; position:relative; top:150px; margin-right:auto; margin-left:auto; border:1px hidden #000;\">");
      out.println("<p class=\"paragraph\"> Username: " + accountInfo.get(3) + "</p>");
      out.println("<p class=\"paragraph\"> First name: " + accountInfo.get(0) + "</p>");
      out.println("<p class=\"paragraph\"> Last name: " + accountInfo.get(1) + "</p>");
      out.println("<p class=\"paragraph\"> Email address: " + accountInfo.get(2) + "</p>");
      out.println("<form action=\"buddyuser?user=" + user + "\" method=\"post\" />");
      out.println("<input type=\"submit\" value=\"View Uploads\" />");
      out.println("</form>");
      out.println("</div>");
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String user = request.getParameter("user");
    ArrayList<String> uploads = DatabaseHandler.userUploads(user);

    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    Header.drawHeader(out);
    out.println("<head>");
    out.println("<title>Valendra</title>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"account.css\">");
    out.println("</head>");

    out.println("<div>");
    for (String upload : uploads) {
      out.println("<a href=\"result?document=" + upload + "\">" + upload + "</a><br />");
    }
    out.println("</div>");
  }
}
