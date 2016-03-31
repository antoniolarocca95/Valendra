package org.valendra.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.valendra.accounts.AccountsLogin;

public class DatabaseHandler {

  public static Connection connectToDatabase() {
    Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager
          .getConnection("jdbc:sqlite:/opt/tomcat/webapps/Valendra/resources/Database.db");
      System.out.println("Database connected successful");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return c;
  }

  /*
   * query our database based on a string will return a resultset object that we can use to extract
   * the info from our query
   */
  public static boolean loginUser(String username, String password) {
    String query = "SELECT * FROM tab_acc WHERE username = '" + username + "' AND password = '"
        + password + "'";
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

  public static void deleteUser(String username) {
    Connection c = DatabaseHandler.connectToDatabase();
    try {
      c.setAutoCommit(false);
      Statement stment = c.createStatement();
      String sql = "delete from tab_acc where username='" + username + "';";
      stment.executeUpdate(sql);
      stment.close();
      c.commit();
      c.close();
    } catch (Exception e) {

    }
  }

  public static ArrayList<String> findBuddy(String parameter, String input) {

    String query = "select * from tab_acc where " + parameter + " like '%" + input + "%';";
    Connection c = DatabaseHandler.connectToDatabase();
    ResultSet res = null;
    Statement stment = null;
    ArrayList<String> buddies = new ArrayList<String>();
    try {
      c.setAutoCommit(false);
      stment = c.createStatement();
      res = stment.executeQuery(query);
      c.commit();
      if (res == null) {
        return buddies;
      }
      while (res.next()) {
        buddies.add(res.getString(1));
        buddies.add(res.getString(2));
        buddies.add(res.getString(3));
        buddies.add(res.getString(4));

      }

      res.close();
      stment.close();
      c.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return buddies;

  }

  public static void addUser(String firstname, String lastname, String email, String username,
      String password) {
    Connection c = DatabaseHandler.connectToDatabase();
    try {
      c.setAutoCommit(false);
      Statement stment = c.createStatement();
      String sql = "insert into tab_acc " + "values ('" + firstname + "', '" + lastname + "', '"
          + email + "', '" + username + "', '" + password + "');";
      stment.executeUpdate(sql);
      stment.close();
      c.commit();
      c.close();
    } catch (Exception e) {

    }
  }

  public static void comment(int rating, String comment, String file) {
    String username = AccountsLogin.LOGGED_IN;
    String DELQuery =
        "DELETE from tab_rating where username = '" + username + "' AND file = '" + file + "';";
    String commentQuery =
        "INSERT INTO tab_comment VALUES('" + file + "', '" + username + "', '" + comment + "');";
    String ratingQuery = "INSERT INTO tab_rating VALUES('" + file + "', '" + username + "', "
        + Integer.toString(rating) + ");";

    Connection c = connectToDatabase();
    Statement stment = null;
    if (rating == -1) {
      try {
        c.setAutoCommit(false);
        stment = c.createStatement();
        stment.executeUpdate(commentQuery);
        stment.executeUpdate(ratingQuery);
        c.commit();
        stment.close();
        c.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    } else {
      try {
        c.setAutoCommit(false);
        stment = c.createStatement();
        stment.executeUpdate(DELQuery);
        stment.executeUpdate(commentQuery);
        stment.executeUpdate(ratingQuery);
        c.commit();
        stment.close();
        c.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static ArrayList<String> getAccountInformation(String user) {
    String query = "select * from tab_acc where username ='" + user + "';";
    Connection c = DatabaseHandler.connectToDatabase();
    ResultSet res = null;
    Statement stment = null;
    ArrayList<String> accountInfo = new ArrayList<String>();
    try {
      c.setAutoCommit(false);
      stment = c.createStatement();
      res = stment.executeQuery(query);
      c.commit();
      if (res == null) {
        return accountInfo;
      }
      while (res.next()) {
        accountInfo.add(res.getString(1));
        accountInfo.add(res.getString(2));
        accountInfo.add(res.getString(3));
        accountInfo.add(res.getString(4));
        accountInfo.add(res.getString(5));
      }

      res.close();
      stment.close();
      c.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return accountInfo;

  }

  public static ArrayList<String> getComments(String file) {
    String query = "SELECT * FROM tab_comment WHERE file = '" + file + "';";
    Connection c = DatabaseHandler.connectToDatabase();
    ResultSet res = null;
    Statement stment = null;
    ArrayList<String> comments = new ArrayList<String>();
    try {
      c.setAutoCommit(false);
      stment = c.createStatement();
      res = stment.executeQuery(query);
      c.commit();
      if (res == null) {
        return comments;
      }
      while (res.next()) {
        comments.add(res.getString(3));
      }

      res.close();
      stment.close();
      c.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return comments;
  }

  public static String getRating(String file) {
    String query = "SELECT * FROM tab_rating WHERE file = '" + file + "';";
    Connection c = DatabaseHandler.connectToDatabase();
    ResultSet res = null;
    Statement stment = null;
    int stars = 0;
    int total = 0;
    try {
      c.setAutoCommit(false);
      stment = c.createStatement();
      res = stment.executeQuery(query);
      c.commit();

      while (res.next()) {
        System.out.println("in while");
        stars += Integer.parseInt((res.getString(3)));
        total++;
      }

      if (total == 0) {
        return "No ratings yet";
      }

      stars /= total;

      res.close();
      stment.close();
      c.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return Integer.toString(stars) + " / 5";
  }

}
