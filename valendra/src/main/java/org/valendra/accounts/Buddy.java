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
    if (AccountsLogin.LOGGED_IN.equals("false")) {
      out.println(
          "<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
    } else {
      out.println("<head>");
      out.println("<title>Valendra</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"buddy.css\">");
      out.println("</head>");
      out.println("<h1>Find a study buddy</h1>");
      out.println("<form action=\"buddy\" method=\"post\">");
      out.println("<input type=\"radio\" name=\"buddy\" value=\"firstname\"><div id=\"t\">First Name</div><br>");
      out.println("<input type=\"radio\" name=\"buddy\" value=\"lastname\"><div id=\"t\">Last Name</div><br>");
      out.println("<input type=\"radio\" name=\"buddy\" value=\"email\"><div id=\"t\">Email Address</div><br>");
      out.println("<input type=\"radio\" name=\"buddy\" value=\"username\"><div id=\"t\">Username</div><br>");
      out.println("<input type=\"text\" name=\"search\" placeholder=\"Search\"><br>");
      out.println("<input type=\"submit\" value=\"Find Buddy\" />");
      out.println("<br />");
      out.println("</form>");
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    if (AccountsLogin.LOGGED_IN.equals("false")) {
      out.println(
          "<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
    } else {
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
        out.println(
            "<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/buddy\" />");
      }

      if (input.equalsIgnoreCase("")) {
        out.println("<script>alert(\"Missing input!\");</script>");
        out.println(
            "<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/buddy\" />");
      }

      else if (!input.equalsIgnoreCase("")) {
        ArrayList<String> buddies = DatabaseHandler.findBuddy(parameter, input);
        String text = "";
        for (int i = 0; i < buddies.size(); i += 1) {
          text += buddies.get(i) + " ";
          if (((i + 1) % 4) == 0) {
            out.println("<a href=\"buddyuser?user=" + buddies.get(i) + "\"/>" + text + "</a><br>");
            text = "";
          }
        }
      }
    }
  }
}
