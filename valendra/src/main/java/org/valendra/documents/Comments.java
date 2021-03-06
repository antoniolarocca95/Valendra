package org.valendra.documents;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.valendra.accounts.AccountsLogin;
import org.valendra.database.DatabaseHandler;
import org.valendra.valendra.Header;

public class Comments extends HttpServlet {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    if (AccountsLogin.LOGGED_IN.equals("false")) {
      out.println(
          "<meta http-equiv=\"refresh\" content=\"0; url=/Valendra/login\" />");
    } else {
      Header.drawHeader(out);
      out.println("<head>");
      out.println("<title>Valendra</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"comments.css\">");
      out.println("</head>");
      out.println("<div>");
      String docName = request.getParameter("document");
      out.println(DatabaseHandler.getRating(docName) + "<br /><br />");
      ArrayList<String[]> comments = DatabaseHandler.getComments(docName);
      for (String[] comment : comments) {
        out.println(comment[1] + " -" + comment[0] + "<br />");
      }
      out.println("</div>");
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    if (AccountsLogin.LOGGED_IN.equals("false")) {
      out.println(
          "<meta http-equiv=\"refresh\" content=\"0; url=/Valendra/login\" />");
    } else {
      String rating = request.getParameter("rating");
      int r = -1;
      if ("1".equals(rating)) {
        r = 1;
      } else if ("2".equals(rating)) {
        r = 2;
      } else if ("3".equals(rating)) {
        r = 3;
      } else if ("4".equals(rating)) {
        r = 4;
      } else if ("5".equals(rating)) {
        r = 5;
      }
      String comment = request.getParameter("comments");
      String docName = request.getParameter("document");
      DatabaseHandler.comment(r, comment, docName);
      out.print("<script>function myFunction() {alert(\"Comment added\")}; myFunction();</script>");
      out.println(
          "<meta http-equiv=\"refresh\" content=\"0; url=/Valendra/result?document=" + docName + "\"/>");
    }
  }
}
