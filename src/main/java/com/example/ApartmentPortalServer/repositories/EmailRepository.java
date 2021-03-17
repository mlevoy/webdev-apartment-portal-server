package com.example.ApartmentPortalServer.repositories;

import com.example.ApartmentPortalServer.models.Email;
import com.example.ApartmentPortalServer.models.Property;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<Email, String> {


}
