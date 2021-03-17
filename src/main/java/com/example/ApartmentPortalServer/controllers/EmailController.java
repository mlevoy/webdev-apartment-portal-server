package com.example.ApartmentPortalServer.controllers;

import com.example.ApartmentPortalServer.models.Email;
import com.example.ApartmentPortalServer.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*")
public class EmailController {

  @Autowired
  EmailService service;


  @PostMapping("/api/sendemail")
  public void sendEmail(@RequestBody Email email) throws IOException, MessagingException {
       service.sendmail(email);
  }


  @GetMapping("/api/emails")
  List<Email> findUsers() {
    return service.findEmails();
  }

}
