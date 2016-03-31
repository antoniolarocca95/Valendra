package org.valendra.valendra;

public class Header {

  public static void drawHeader(java.io.PrintWriter out) {
    out.println("<ul>");
    out.println("<li><a href=\"/Valendra/login\">Logout</a></li>");
    out.println("<li><a href=\"/Valendra/buddy\">Buddy</a></li>");
    out.println("<li><a href=\"/Valendra/upload\">Upload</a></li>");
    out.println("<li><a href=\"/Valendra/account\">Account</a></li>");
    out.println("<li><a href=\"/Valendra/home\">Search</a></li>");
    out.println("</ul>");
  }
}
