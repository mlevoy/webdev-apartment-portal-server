package com.example.ApartmentPortalServer.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document(collection ="message")
public class Message {

  @Id
  private String id;

  private User fromUser;
  private User toUser;
  private String message;
  private Timestamp time;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public User getFromUser() {
    return fromUser;
  }

  public void setFromUser(User fromUser) {
    this.fromUser = fromUser;
  }

  public User getToUser() {
    return toUser;
  }

  public void setToUser(User toUser) {
    this.toUser = toUser;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Timestamp getTime() {
    return time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }


}
