package com.example.ApartmentPortalServer.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "user")
public class User {

  @Id
  private String id;
  private String userName;
  private String firstName;
  private String lastName;
  private String password;
  private String verifyPassword;
  private String email;
  private String phone;
  private String zipCode;

  private CustomerType customerType;

  private List<String> favouriteProperties;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    id = id;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public CustomerType getCustomerType() {
    return customerType;
  }

  public void setCustomerType(CustomerType customerType) {
    this.customerType = customerType;
  }

//  public List getProperty() {
//    return property;
//  }
//
//  public void setProperty(List property) {
//    this.property = property;
//  }


  public String getVerifyPassword() {
    return verifyPassword;
  }

  public void setVerifyPassword(String verifyPassword) {
    this.verifyPassword = verifyPassword;
  }

  public List<String> getFavouriteProperties() {
    return favouriteProperties;
  }

  public void setFavouriteProperties(List<String> favouriteProperties) {
    this.favouriteProperties = favouriteProperties;
  }
}
