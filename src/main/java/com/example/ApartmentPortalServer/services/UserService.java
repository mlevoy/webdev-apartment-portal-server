package com.example.ApartmentPortalServer.services;

import com.example.ApartmentPortalServer.models.CustomerType;
import com.example.ApartmentPortalServer.models.User;
import com.example.ApartmentPortalServer.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public ResponseEntity createUser(HttpSession session, User user) {
    if ( userRepository.existsByUserName(user.getUserName()) ) {
      return ResponseEntity.badRequest().body(user);
    }
    User newUser = userRepository.save(user);
    session.setAttribute("profile", newUser);

    // return newUser;
    return ResponseEntity.status(HttpStatus.OK).body(newUser);
  }

  public List<User> findUsers() {
    return userRepository.findAll();
  }

  public User findUserByUserId(String id) {
    return  userRepository.findUsersById(id); //.orElse(null);
  }

  public int deleteUser(String userName) {
    userRepository.deleteByuserName(userName);
    return 1;
  }


  public ResponseEntity updateUser(HttpSession session,String username, User user) {
    User oldUser =
            userRepository.findUsersByuserName(username).get(0);
  if (oldUser!=null) {
    oldUser.setPassword(user.getPassword());
    oldUser.setVerifyPassword(user.getVerifyPassword());
    oldUser.setFirstName(user.getFirstName());
    oldUser.setLastName(user.getLastName());
    oldUser.setEmail(user.getEmail());
    oldUser.setPhone(user.getPhone());
    oldUser.setZipCode(user.getZipCode());
    oldUser.setCustomerType(user.getCustomerType());
    oldUser.setFavouriteProperties(user.getFavouriteProperties());
    userRepository.save(oldUser);
    session.setAttribute("profile", oldUser);
    return ResponseEntity.status(HttpStatus.OK).body(oldUser);
  }
  else {
    return ResponseEntity.badRequest().body(user);
    }
  }


  public ResponseEntity userLogin(HttpSession session, User newUser) {

    String username = newUser.getUserName();
    String password = newUser.getPassword();
    if ( username == null || password == null || username.isEmpty() || password.isEmpty() ) {
      return ResponseEntity.badRequest().body(newUser);
    }
    User profile =
            userRepository.findUsersByuserName(username).get(0);
    if ( profile != null ) {
      if ( password.equals(profile.getPassword()) ) {
        session.setAttribute("profile", profile);
        return ResponseEntity.status(HttpStatus.OK).body(profile);
      }
      return ResponseEntity.badRequest().body(profile);
    }
    return ResponseEntity.badRequest().body(profile);
  }


  public ResponseEntity userProfile(HttpSession session) {

   User profile = (User) session.getAttribute("profile");
   if (profile==null){
     return ResponseEntity.badRequest().body(profile);
   }
   else{
     return ResponseEntity.status(HttpStatus.OK).body(profile);
   }
 }

  public void userLogout(HttpSession session) {
   session.invalidate();
  }

}
