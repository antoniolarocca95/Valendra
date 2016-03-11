package account;

import java.sql.Date;

public class Accounts {
  /*
   * Static variables to keep track of account types
   */
  
  static final int TYPE_STUDENT = 0;
  static final int TYPE_PROFESSOR = 1;

  /**
   * All variables needed for an account
   */

  int accountType;
  String firstName;
  String lastName;
  String email;
  Date birthday;

  public Accounts() {

  }

  /*
   * Start of Getters and Setters, more functions to be added
   */
  public int getAccountType() {
    return accountType;
  }

  public void setAccountType(int accountType) {
    this.accountType = accountType;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
}
