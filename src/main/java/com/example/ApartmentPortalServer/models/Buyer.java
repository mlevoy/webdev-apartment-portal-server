package com.example.ApartmentPortalServer.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="buyer")
public class Buyer {

  @Id
  private User user;

  private List<Property> property;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Property> getProperty() {
    return property;
  }

  public void setProperty(List<Property> property) {
    this.property = property;
  }

}
