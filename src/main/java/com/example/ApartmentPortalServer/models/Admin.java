package com.example.ApartmentPortalServer.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="admin")
public class Admin {

  @Id
  private Integer id;
  private User user;

  //May be we can do 1-5 permissions
  // 1 - allow user to list a property
  // 2 - allow user to buy a property
  // 3 - allow user to list and buy property
  // 4 - allow user for nothing
  // 5 - allow user everything i.e. admin rights (only one will have it)
  private Integer permission;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getPermission() {
    return permission;
  }

  public void setPermission(Integer permission) {
    this.permission = permission;
  }
}
