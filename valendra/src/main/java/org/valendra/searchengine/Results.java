package org.valendra.searchengine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.valendra.accounts.AccountsLogin;

public class Results extends HttpServlet {
  private static final long serialVersionUID = 1L;

  String FILES_DIRECTORY;

  public void init() {
    // Get the file location where it would be stored.
    FILES_DIRECTORY = getServletContext().getInitParameter("file-upload");
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    String docName = request.getParameter("document");

    out.println("<a href=\"filesDirectory/" + docName + "\" download=\"" + docName
        + "\">Download this file</a>");

    if (AccountsLogin.LOGGED_IN.equals("false")) {
      out.println(
          "<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Valendra/login\" />");
    } else {
      out.println("<html>");
      out.println("<head>");
      out.println("<title>" + docName + "</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"doc.css\">");
      out.println("<script src=\"doc.js\"></script>");
      out.println("</head>");
      out.println("<body>");
      out.println("<div id=\"myNav\" class=\"overlay\">");
      out.println(
          "<a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav()\">&times;</a>");
      out.println("<div class=\"overlay-content\">");
      out.println("<form action=\"comments?document=" + docName + "\" method=\"post\">");
      // Starting the star-rating
      out.println("<strong class = \"class\">Choose a rating</strong>");
      out.println("<span class = \"star-rating\">");
      out.println("<input type = \"radio\" name=\"rating\" value = \"1\"><i></i>");
      out.println("<input type = \"radio\" name=\"rating\" value = \"2\"><i></i>");
      out.println("<input type = \"radio\" name=\"rating\" value = \"3\"><i></i>");
      out.println("<input type = \"radio\" name=\"rating\" value = \"4\"><i></i>");
      out.println("<input type = \"radio\" name=\"rating\" value = \"5\"><i></i>");
      out.println("</span><br>");
      // Ending Star-rating
      // Starting the comment form
      out.println(
          "<textarea placeholder = \"Comment\" wrap = \"hard\" cols = \"20\" id = \"commentbox\" type = \"text\" name = \"comments\"></textarea><br>");
      out.println("<input type=\"submit\" class=\"float\" id=\"sub\" value=\"Submit\" />");
      out.println("</form>");
      out.println("<a href=\"comments?document=" + docName
          + "\" class=\"float\" id=\"Comments\" />Comments</a>");
      out.println("</div>");
      out.println("</div>");
      out.println("<button class=\"o\" onclick=\"openNav()\"></button>");

      out.println("<div id = \"text\">");
      if (docName.substring(docName.length() - 3, docName.length()).equals("pdf")) {
        String[] text = ParserPDF.parse(FILES_DIRECTORY + docName);
        for (String line : text) {
          out.print(line + "<br />");
        }
      } else {
        FileReader fr = new FileReader(FILES_DIRECTORY + docName);
        BufferedReader br = new BufferedReader(fr);
        String line;
        out.println("<xmp id=\"text\">");
        while ((line = br.readLine()) != null) {
          out.println(line);
        }
        out.println("</xmp>");
        br.close();
      }
      out.println("</div>");
      out.println("</body>");
      out.println("</html>");
    }
  }
}
