package database;

import java.sql.*;

import searchengine.SearchEngine;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class DatabaseUploading {

	/* Returns a connection to the database */
	public static Connection connectToDatabase() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			System.out.println("Database connected successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public static void addDocument(String pathname) throws IOException {
		File source = new File(pathname);
		String filename = source.getName();
		File dest = new File(SearchEngine.FILES_DIRECTORY + "/" + filename);
		dest.createNewFile();
		FileUtils.copyFile(source, dest);
		Connection c = connectToDatabase();
		try {
			String readtext = null;
			String data = null;
			File doc = new File(pathname);
			FileReader read = new FileReader(doc);
			@SuppressWarnings("resource")
			BufferedReader strm = new BufferedReader(read);

			while ((readtext = strm.readLine()) != null) {
				data = data + readtext;
			}

			Statement stment = c.createStatement();
			String sql = "INSERT INTO tab_doc values(\"" + pathname + "\",\"" + data + "\");";

			stment.executeUpdate(sql);
			stment.close();
			c.commit();
			c.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public static void addUser(String firstname, String lastname, String email, String username, String pass) {
		Connection c = connectToDatabase();
		try {
			c.setAutoCommit(false);
			Statement stment = c.createStatement();
			String sql = "insert into tab_acc " + "values (\"" + firstname + "\", \"" + lastname + "\", \"" + email
					+ "\", \"" + username + "\", \"" + pass + "\");";
			stment.executeUpdate(sql);
			stment.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void likeADocument() {

	}

	public static void commentOnADocument() {

	}

}
