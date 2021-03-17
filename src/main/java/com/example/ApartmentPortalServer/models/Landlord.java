package com.example.ApartmentPortalServer.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="landlord")
public class Landlord {

  private List<Property> property;

  @Id
  private User user ;


  public List<Property> getProperty() {
    return property;
  }

  public void setProperty(List<Property> property) {
    this.property = property;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }




}
