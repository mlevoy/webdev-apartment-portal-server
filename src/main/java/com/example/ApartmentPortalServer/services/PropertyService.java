package com.example.ApartmentPortalServer.services;

import com.example.ApartmentPortalServer.models.Amenities;
import com.example.ApartmentPortalServer.models.Property;
import com.example.ApartmentPortalServer.models.User;
import com.example.ApartmentPortalServer.repositories.PropertyApiDataRepository;
import com.example.ApartmentPortalServer.repositories.PropertyRepository;
import com.example.ApartmentPortalServer.repositories.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PropertyService {

  @Autowired
  PropertyRepository propertyRepository;


  @Autowired
  UserRepository userRepository;

  @Autowired
  PropertyApiDataRepository propertyApiDataRepository;


  public ResponseEntity<String> addProperty(Property property) {
    Property newProperty = propertyRepository.save(property);
    newProperty.setDatePosted(new Date());
    return ResponseEntity.status(HttpStatus.OK).body("Property created ");

  }

  public List<Property> findProperties() {
    return propertyRepository.findAll();
  }

  public Property findPropertyById(String pId) {
    if (propertyRepository.findById(pId).isPresent()) {
      return propertyRepository.findById(pId).get();
    } else {
      try {
        return propertyApiDataRepository.getPropertyById(pId);
      } catch (ParseException e) {
        e.printStackTrace();
        return null;
      }
    }
  }

  public int deleteProperty(String pId) {
    propertyRepository.deleteById(pId);
    return 1;
  }


  public int addPropertyToUserFavourites(String username, String propertyid) {
    User user = userRepository.findUsersByuserName(username).get(0);
    List<String> propertyList = user.getFavouriteProperties();
    if (propertyList == null) {
      propertyList = new ArrayList<>();
      propertyList.add(propertyid);
      user.setFavouriteProperties(propertyList);
      userRepository.save(user);
      return 1;
    } else {
      propertyList.add(propertyid);
      user.setFavouriteProperties(propertyList);
      userRepository.save(user);
      return 1;
    }

  }

  public int deletePropertyFromUserFavourites(String username, String propertyid) {
    User user = userRepository.findUsersByuserName(username).get(0);
    List<String> propertyList = user.getFavouriteProperties();
    for (String temp : propertyList) {
      if (propertyid.equalsIgnoreCase(temp)) {
        propertyList.remove(temp);
        user.setFavouriteProperties(propertyList);
        userRepository.save(user);
        return 1;
      }
    }
    return 1;
  }

  public List<Property> findPropertiesByCity(String city) {
    return propertyRepository.findPropertiesByCity(city);
  }


  public List<String> getAllFavoriteProperties(String username) {

    User user = userRepository.findUsersByuserName(username).get(0);
    return user.getFavouriteProperties();
  }


  public Map<String, Amenities> getAllAmenities() {
    Map<String, Amenities> map = new HashMap<String, Amenities>();
    for (Amenities amenity : Amenities.values()) {
      map.put(amenity.getAmenity(), amenity);
    }
    return map;
  }

  public List<Property> findPropertiesBySearch(String zipCode, String city) {
    List<Property> searchResult = propertyRepository.findPropertiesByCity(city);
    try {
      searchResult.addAll(searchResult.size(), propertyApiDataRepository.getProperties(zipCode));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return searchResult;
  }

}



