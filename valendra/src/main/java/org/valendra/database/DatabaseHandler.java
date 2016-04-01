package org.valendra.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    PreparedStatement prep = null;
    String query = "SELECT * FROM tab_acc WHERE username = ? AND password = ?";
    Connection c = connectToDatabase();
    ResultSet res = null;
    Boolean ret = true;
    try {
      c.setAutoCommit(false);
      prep = c.prepareStatement(query);
      prep.setString(1, username);
      prep.setString(2, password);
      res = prep.executeQuery();
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
      prep.close();
      c.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return ret;
  }

  public static void deleteUser(String username) {
    Connection c = DatabaseHandler.connectToDatabase();
    PreparedStatement prep = null;
    try {
      c.setAutoCommit(false);
      String sql = "delete from tab_acc where username=?;";
      prep = c.prepareStatement(sql);
      prep.setString(1, username);
      prep.executeUpdate();
      c.commit();
      prep.close();
      c.close();
    } catch (Exception e) {

    }
  }

  public static ArrayList<String> findBuddy(String parameter, String input) {

    String query = "select * from tab_acc where " + parameter + " like ?;";
    Connection c = DatabaseHandler.connectToDatabase();
    ResultSet res = null;
    PreparedStatement prep = null;
    ArrayList<String> buddies = new ArrayList<String>();
    try {
      c.setAutoCommit(false);
      prep = c.prepareStatement(query);
      input = "%" + input + "%";
      prep.setString(1, input);
      res = prep.executeQuery();
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
      prep.close();
      c.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return buddies;

  }

  public static void addUser(String firstname, String lastname, String email, String username,
      String password) {
    Connection c = DatabaseHandler.connectToDatabase();
    PreparedStatement prep = null;
    try {
      c.setAutoCommit(false);
      String sql = "insert into tab_acc values (?, ?, ?, ?, ?);";
      prep = c.prepareStatement(sql);
      prep.setString(1, firstname);
      prep.setString(2, lastname);
      prep.setString(3, email);
      prep.setString(4, username);
      prep.setString(5, password);
      prep.executeUpdate();
      c.commit();
      prep.close();
      c.close();
    } catch (Exception e) {

    }
  }

  public static void comment(int rating, String comment, String file) {
    String username = AccountsLogin.LOGGED_IN;
    String DELQuery = "DELETE from tab_rating where username = ? AND file = ?;";
    String commentQuery = "INSERT INTO tab_comment VALUES(?, ?, ?);";
    String ratingQuery = "INSERT INTO tab_rating VALUES(?, ?, ?);";

    Connection c = connectToDatabase();
    PreparedStatement prepDEL = null;
    PreparedStatement prepCOM = null;
    PreparedStatement prepRATE = null;

    if (rating == -1) {
      try {
        c.setAutoCommit(false);
        prepCOM = c.prepareStatement(commentQuery);
        prepRATE = c.prepareStatement(ratingQuery);

        prepCOM.setString(1, file);
        prepCOM.setString(2, username);
        prepCOM.setString(3, comment);

        prepRATE.setString(1, file);
        prepRATE.setString(2, username);
        prepRATE.setString(3, Integer.toString(rating));

        prepCOM.executeUpdate();
        prepRATE.executeUpdate();

        c.commit();

        prepCOM.close();
        prepRATE.close();
        c.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    } else {
      try {
        c.setAutoCommit(false);
        prepDEL = c.prepareStatement(DELQuery);
        prepCOM = c.prepareStatement(commentQuery);
        prepRATE = c.prepareStatement(ratingQuery);

        prepDEL.setString(1, username);
        prepDEL.setString(2, file);

        prepCOM.setString(1, file);
        prepCOM.setString(2, username);
        prepCOM.setString(3, comment);

        prepRATE.setString(1, file);
        prepRATE.setString(2, username);
        prepRATE.setString(3, Integer.toString(rating));

        prepDEL.executeUpdate();
        prepCOM.executeUpdate();
        prepRATE.executeUpdate();

        c.commit();

        prepDEL.close();
        prepCOM.close();
        prepRATE.close();
        c.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static ArrayList<String> getAccountInformation(String user) {
    String query = "select * from tab_acc where username = ?;";
    Connection c = DatabaseHandler.connectToDatabase();
    ResultSet res = null;
    PreparedStatement prep = null;

    ArrayList<String> accountInfo = new ArrayList<String>();
    try {
      c.setAutoCommit(false);
      prep = c.prepareStatement(query);
      prep.setString(1, user);
      res = prep.executeQuery();
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
      prep.close();
      c.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return accountInfo;

  }

  public static ArrayList<String[]> getComments(String file) {
    String query = "SELECT * FROM tab_comment WHERE file = ?;";
    Connection c = DatabaseHandler.connectToDatabase();
    ResultSet res = null;
    PreparedStatement prep = null;
    ArrayList<String[]> comments = new ArrayList<String[]>();
    try {
      c.setAutoCommit(false);
      prep = c.prepareStatement(query);
      prep.setString(1, file);
      res = prep.executeQuery();
      c.commit();
      if (res == null) {
        return comments;
      }
      while (res.next()) {
        String[] comment = {res.getString(2), res.getString(3)};
        comments.add(comment);
      }

      res.close();
      prep.close();
      c.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return comments;
  }

  public static String getRating(String file) {
    String query = "SELECT * FROM tab_rating WHERE file = ?;";
    Connection c = DatabaseHandler.connectToDatabase();
    ResultSet res = null;
    PreparedStatement prep = null;
    int stars = 0;
    int total = 0;
    try {
      c.setAutoCommit(false);
      prep = c.prepareStatement(query);
      prep.setString(1, file);
      res = prep.executeQuery();
      c.commit();

      while (res.next()) {
        stars += Integer.parseInt((res.getString(3)));
        total++;
      }

      if (total == 0) {
        return "No ratings yet";
      }

      stars /= total;

      res.close();
      prep.close();
      c.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return "Total Rating: " + Integer.toString(stars) + " / 5";
  }

  public static void upload(String fileName) {
    String query = "INSERT INTO tab_upload VALUES(?, ?)";
    Connection c = DatabaseHandler.connectToDatabase();
    PreparedStatement prep = null;
    try {
      c.setAutoCommit(false);
      prep = c.prepareStatement(query);
      prep.setString(1, AccountsLogin.LOGGED_IN);
      prep.setString(2, fileName);
      prep.executeUpdate();
      c.commit();
      prep.close();
      c.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static ArrayList<String> userUploads(String username) {
    String query = "SELECT * FROM tab_upload WHERE username = ?";
    Connection c = DatabaseHandler.connectToDatabase();
    PreparedStatement prep = null;
    ResultSet res = null;
    ArrayList<String> uploads = new ArrayList<String>();
    try {
      c.setAutoCommit(false);
      prep = c.prepareStatement(query);
      prep.setString(1, username);
      res = prep.executeQuery();
      c.commit();
      while (res.next()) {
        uploads.add(res.getString(2));
      }
      res.close();
      prep.close();
      c.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return uploads;
  }
}
