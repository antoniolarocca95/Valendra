package database;

import java.sql.*;


public class DatabaseUploading{

	/*Returns a connection to the database*/
	public static Connection connectToDatabase(){
		Connection c = null;
		try{
			Class.forName("org.sqlite.JBDC");
			c = DriverManager.getConnection("jbdc:sqlite:test.db");
			System.out.println("Database connected successful");
		}catch(Exception e){
			System.out.println("Database opening unsuccessful");
		}
		return c;
	}

	/*Creates an Account table and dummy entries for testing*/
	private static void createAccountTable(){
		Connection c = connectToDatabase();
		try{
			Statement stment = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS tab_acc(" +
					"firstname INTEGER," +
					"lastname TEXT," +
					"email TEXT," +
					"username TEXT PRIMARY KEY," +
					"password TEXT)";
			stment.executeUpdate(sql);
			sql = "INSERT INTO tab_acc(" +
					"'first1', 'last1', 'email1', 'user1', 'pass1')";
			stment.executeUpdate(sql);
			stment.close();
			c.close();

		}catch(SQLException e){
			System.out.println("Table creation unsuccessful");
		}
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
			Statement stment = c.createStatement();
			String sql = "INSERT INTO tab_acc(" +
					firstname + "," +
					lastname + "," +
					email + "," +
					username + "," +
					pass + ")";
			stment.executeUpdate(sql);
			stment.close();
			c.close();
		}catch(Exception e){
			System.out.println("Could not register user");
		}

	}

	public static void likeADocument()
	{

	}

	public static void commentOnADocument()
	{
		
	}

}
