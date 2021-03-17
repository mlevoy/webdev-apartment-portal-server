package com.example.ApartmentPortalServer.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="customerType")
public enum CustomerType {
  BUYER,LANDLORD,ADMIN
}
