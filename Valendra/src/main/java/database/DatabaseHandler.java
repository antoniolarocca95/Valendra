package database;

import java.sql.*;

public class DatabaseHandler
{

	/*
	* query our database based on a string
	* will return a resultset object that we can use to extract the info from our query
	*/
	public static ResultSet query(String query)
	{
		Connection c = DatabaseUploading.connectToDatabase();
		Statement stment = c.createStatement();
		ResultSet res = stment.execute()
		return res;
	}

	public static boolean loginUser(String username, String password){
		String sql = "SELECT * FROM tab_acc acc WHERE acc.username = " + username +
				"AND acc.password = " + password;
		ResultSet result = query(sql);
		if (result == null){
			return false;
		}
		else{
			return true;
		}
	}

	/*
	*takes in a string and returns nothing
	* will just add a row to the database
	*/
	public static void add(String query)
	{

	}



}
