package com.example.ApartmentPortalServer.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="email")
public class Email {

  @Id
  private String id;

  private String mailFrom;
  private String mailTo;
  private String mailSubject;
  private String mailContent;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMailFrom() {
    return mailFrom;
  }

  public void setMailFrom(String mailFrom) {
    this.mailFrom = mailFrom;
  }

  public String getMailTo() {
    return mailTo;
  }

  public void setMailTo(String mailTo) {
    this.mailTo = mailTo;
  }

  public String getMailSubject() {
    return mailSubject;
  }

  public void setMailSubject(String mailSubject) {
    this.mailSubject = mailSubject;
  }

  public String getMailContent() {
    return mailContent;
  }

  public void setMailContent(String mailContent) {
    this.mailContent = mailContent;
  }
}
