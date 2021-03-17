package com.example.ApartmentPortalServer.controllers;

import com.example.ApartmentPortalServer.models.Amenities;
import com.example.ApartmentPortalServer.models.CustomerType;
import com.example.ApartmentPortalServer.models.Property;
import com.example.ApartmentPortalServer.services.PropertyService;
import com.example.ApartmentPortalServer.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class PropertyController {

  @Autowired
  PropertyService service;

  @PostMapping("/api/properties")
  public ResponseEntity<String> addProperty(@RequestBody Property property) {
    return service.addProperty(property);
  }

  @GetMapping("/api/properties")
  List<Property> findProperties() {
    return service.findProperties();
  }

  @GetMapping("/api/search/properties/{zipcode}/{city}")
  List<Property> findPropertiesByZipcode(@PathVariable("zipcode") String zipCode,
                                         @PathVariable("city") String city) {
    return service.findPropertiesBySearch(zipCode, city);
  }

  @GetMapping("/api/properties/{pid}")
  Property findPropertyById(@PathVariable("pid") String pId) {
    return service.findPropertyById(pId);
  }

  @DeleteMapping("/api/properties/{pid}")
  public int deletePropertyById(@PathVariable("pid") String pId) {
    return service.deleteProperty(pId);
  }

  @PostMapping("/api/users/{username}/properties/{id}")
  public int addPropertyFavourite(@PathVariable("username") String username,
                                  @PathVariable("id") String propertyid) {
    return service.addPropertyToUserFavourites(username, propertyid);
  }


  @DeleteMapping("/api/users/{username}/properties/{id}")
  public int deletePropertyFavourite(@PathVariable("username") String username,
                                     @PathVariable("id") String propertyid) {
    return service.deletePropertyFromUserFavourites(username, propertyid);
  }


  @GetMapping("/api/users/{username}/properties")
  public List<String> getAllFavourites(@PathVariable("username") String username) {
    return service.getAllFavoriteProperties(username);
  }

  @GetMapping("/api/properties/amenities")
  public Map<String, Amenities> getAllAmenities() {

    return service.getAllAmenities();
  }

}

