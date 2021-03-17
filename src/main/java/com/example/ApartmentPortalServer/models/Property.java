package com.example.ApartmentPortalServer.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "property")
public class Property {

  @Id
  private String id;
  private String address;
  private String locality;
  private String type;
  private Integer builtYear;
  private Float size;
  private Integer bed;
  private Double bath;
  private Double amount;
  private String description;
  private List<Amenities> amenities;
  private String city;
  private String name;
  private String state;
  private String seller;
  private String postal;
  private Date dateAvailable;
  private Date datePosted;


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostal() {
    return postal;
  }

  public void setPostal(String postal) {
    this.postal = postal;
  }

  public Integer getBuiltYear() {
    return builtYear;
  }

  public void setBuiltYear(Integer builtYear) {
    this.builtYear = builtYear;
  }

  public List<Amenities> getAmenities() {
    return amenities;
  }

  public void setAmenities(List<Amenities> amenities) {
    this.amenities = amenities;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Date getDateAvailable() {
    return dateAvailable;
  }

  public void setDateAvailable(Date dateAvailable) {
    this.dateAvailable = dateAvailable;
  }

  public Date getDatePosted() {
    return datePosted;
  }

  public void setDatePosted(Date datePosted) {
    this.datePosted = datePosted;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Float getSize() {
    return size;
  }

  public void setSize(Float size) {
    this.size = size;
  }

  public Integer getBed() {
    return bed;
  }

  public void setBed(Integer bed) {
    this.bed = bed;
  }

  public Double getBath() {
    return bath;
  }

  public void setBath(Double bath) {
    this.bath = bath;
  }

}
