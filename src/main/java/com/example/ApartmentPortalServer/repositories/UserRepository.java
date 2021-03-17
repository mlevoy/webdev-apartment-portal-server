package com.example.ApartmentPortalServer.repositories;

import com.example.ApartmentPortalServer.models.User;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {


  boolean existsByUserName(String username);


  List<User> findUsersByuserName(String name);

  @DeleteQuery
  void deleteByuserName(String userName);

  User findUsersById(String id);


}
