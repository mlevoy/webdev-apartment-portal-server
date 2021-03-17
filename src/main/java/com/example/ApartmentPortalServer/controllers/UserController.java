package com.example.ApartmentPortalServer.controllers;

import com.example.ApartmentPortalServer.models.User;
import com.example.ApartmentPortalServer.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {
  @Autowired
  UserService service;

  @PostMapping("/api/register")
  public ResponseEntity createUser(HttpSession session, @RequestBody User user) {
    return service.createUser(session,user);
  }

  @DeleteMapping("/api/users/{username}")
  public int deleteUser(@PathVariable("username") String userName) {
    return service.deleteUser(userName);
  }

  @GetMapping("/api/users")
  List<User> findUsers() {
    return service.findUsers();
  }

  @GetMapping("/api/profile/{id}")
  public User findUserById(@PathVariable("id") String id) {
    return service.findUserByUserId(id);
  }

  @PutMapping("/api/users/{username}")
  public ResponseEntity updateUser(HttpSession session,@PathVariable("username") String username,
                          @RequestBody User user) {
    return service.updateUser(session,username, user);
  }

  @PostMapping("/api/login")
  public ResponseEntity login(HttpSession session,@RequestBody User user) {
    return service.userLogin(session,user);
  }

  @PostMapping("/api/profile")
  public ResponseEntity profile(HttpSession session) {
    return service.userProfile(session);
  }

  @PostMapping("/api/logout")
  public void logout(HttpSession session) {
     service.userLogout(session);

  }


}
