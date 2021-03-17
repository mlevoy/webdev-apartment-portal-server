package com.example.ApartmentPortalServer.repositories;

import com.example.ApartmentPortalServer.models.Property;


import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String> {
  List<Property> findPropertiesByCity(String city);

  @DeleteQuery
  void deleteById(String id);

}
