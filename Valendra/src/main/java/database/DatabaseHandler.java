package database;

import java.sql.*;

public class DatabaseHandler {

	public static Connection connectToDatabase() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:/opt/tomcat/webapps/Valendra/resources/Database.db");
			System.out.println("Database connected successful");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

	/*
	 * query our database based on a string will return a resultset object that
	 * we can use to extract the info from our query
	 */
	public static boolean loginUser(String username, String password) {
		String query = "SELECT * FROM tab_acc WHERE username = \"" + username + "\" AND password = \"" + password
				+ "\"";
		Connection c = connectToDatabase();
		ResultSet res = null;
		Statement stment = null;
		Boolean ret = true;
		try {
			c.setAutoCommit(false);
			stment = c.createStatement();
			res = stment.executeQuery(query);
			c.commit();

		} catch (Exception e) {
		}

		try {
			if (res == null || !res.next()) {
				ret = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stment.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	/*
	 * takes in a string and returns nothing will just add a row to the database
	 */
	public static void add(String query) {

	}

}
