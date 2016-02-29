package project;

import java.sql.Date;

public class Accounts {
  static final int TYPE_STUDENT = 0;
  static final int TYPE_PROFESSOR = 1;
  
  int accountType;
  String firstName;
  String lastName;
  String email;
  Date birthday;
  
  public Accounts(){
    
  }
  
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
