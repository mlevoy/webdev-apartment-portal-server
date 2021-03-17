package com.example.ApartmentPortalServer.services;



import com.example.ApartmentPortalServer.models.Email;
import com.example.ApartmentPortalServer.repositories.EmailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


@Service
public class EmailService {

  @Autowired
  EmailRepository emailRepository;



  public void sendmail(Email email) throws  MessagingException {

    Properties props = new Properties();
    Properties setProps = new Properties(setProperties(props));

    emailRepository.save(email);


    Session session = Session.getInstance(setProps, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("househunter595@gmail.com", "!Househunter595");
      }
    });


    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress("househunter595@gmail.com", false));

    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getMailTo()));
    msg.setSubject(email.getMailSubject());
    msg.setContent(email.getMailContent(), "text/html");
    msg.setSentDate(new Date());
    Transport.send(msg);
  }

  private Properties setProperties(Properties props){
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    return props;
  }

  public List<Email> findEmails() {
    return emailRepository.findAll();
  }

}
