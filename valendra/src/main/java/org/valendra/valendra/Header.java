package org.valendra.valendra;

public class Header {

	public static void drawHeader(java.io.PrintWriter out)
	{
		out.println("<ul>");
		out.println("<li><a href=\"http://localhost:8080/Valendra/home\">Search</a></li>");
		out.println("<li><a href=\"http://localhost:8080/Valendra/login\">Logout</a></li>");
		out.println("<li><a href=\"http://localhost:8080/Valendra/buddy\">Buddy</a></li>");
		out.println("<li><a href=\"http://localhost:8080/Valendra/upload\">Upload</a></li>");
		out.println("<li><a href=\"http://localhost:8080/Valendra/account\">Account</a></li>");
		out.println("</ul>");
		out.println("<div class=\"dropdown\">");
		out.println("<button class=\"dropbtn\" id=\"star\"></button>");
		out.println("<div class=\"dropdown-content\">");
		out.println("</div>");
		out.println("</div>");
	
	
	}
	
}
