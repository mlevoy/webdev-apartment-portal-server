package com.example.ApartmentPortalServer.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "amenities")
public enum Amenities {


  PETS_ALLOWED("Pets"),
  AIR_CONDITIONING("Air Conditioning"),
  LAUNDRY("Laundry"),
  PARKING("Parking"),
  GYM("Gym"),
  PRIVATE_ELEVATOR("Private Elevator"),
  DISHWASHER("Dishwasher"),
  SPACIOUS_BALCONY("Spacious Balcony"),
  WHEELCHAIL_ACCESS("Wheelchair Access");



  private String amenity;

  Amenities(String amenity) {
    this.amenity = amenity;
  }

  public String getAmenity() {
    return amenity;
  }
}
