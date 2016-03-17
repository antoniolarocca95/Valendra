package database;

import java.sql.*;


public class DatabaseUploading{

	/*Returns a connection to the database*/
	public static Connection connectToDatabase(){
		Connection c = null;
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			System.out.println("Database connected successful");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return c;
	}

	public static void addDocument(){
		try{
			


		}catch(Exception e){


		}
	}


	public static void addUser(String firstname, String lastname, 
			String email, String username, String pass){
		Connection c = connectToDatabase();		
		try{
			c.setAutoCommit(false);
			Statement stment = c.createStatement();
			String sql = "insert into tab_acc " +
					"values (\"" + firstname + "\", \"" + lastname + "\", \"" +
					email + "\", \"" + username + "\", \"" + pass + "\");";
			stment.executeUpdate(sql);
			stment.close();
			c.commit();
			c.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}

	public static void likeADocument()
	{

	}

	public static void commentOnADocument()
	{
		
	}

}
