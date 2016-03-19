package database;

import java.sql.*;

public class DatabaseHandler {
	public static Connection connectToDatabase() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
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
	public static ResultSet query(String query) {
		Connection c = connectToDatabase();
		ResultSet res = null;
		try {
			c.setAutoCommit(false);
			Statement stment = c.createStatement();
			res = stment.executeQuery(query);
			stment.close();
			c.commit();
			c.close();
		} catch (Exception e) {

		}
		return res;
	}

	public static boolean loginUser(String username, String password) {
		String sql = "SELECT * FROM tab_acc WHERE username = \"" + username + "\" AND password = \"" + password + "\"";
		ResultSet result = query(sql);
		if (result == null) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * takes in a string and returns nothing will just add a row to the database
	 */
	public static void add(String query) {

	}

}
