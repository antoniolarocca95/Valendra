package org.valendra.documents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Comments extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		String rating = request.getParameter("rating");
		if ("1".equals(rating)) {
			out.println("You chose a rating of 1");
		} else if ("2".equals(rating)) {
			out.println("You chose a rating of 2");
		} else if ("3".equals(rating)) {
			out.println("You chose a rating of 3");
		} else if ("4".equals(rating)) {
			out.println("You chose a rating of 4");
		} else if ("5".equals(rating)) {
			out.println("You chose a rating of 5");
		}
		String comment = request.getParameter("comments");
		out.println(comment);
	}
}
