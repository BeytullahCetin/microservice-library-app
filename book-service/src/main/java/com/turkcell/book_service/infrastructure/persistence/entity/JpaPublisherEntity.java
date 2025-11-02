package com.turkcell.book_service.infrastructure.persistence.entity;

import com.turkcell.book_service.domain.entities.Publisher;
import jakarta.persistence.*;

import java.util.UUID;

/**
 * JPA Entity for Publisher persistence
 */
@Entity
@Table(name = "publishers")
public class JpaPublisherEntity {
    
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "name", nullable = false, unique = true, length = 200)
    private String name;
    
    @Column(name = "address", length = 500)
    private String address;
    
    @Column(name = "website", length = 200)
    private String website;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;
    
    @Column(name = "country", length = 100)
    private String country;
    
    // Constructors
    public JpaPublisherEntity() {
    }
    
    // Conversion methods
    public static JpaPublisherEntity fromDomain(Publisher publisher) {
        JpaPublisherEntity entity = new JpaPublisherEntity();
        entity.setId(publisher.getId().toString());
        entity.setName(publisher.getName());
        entity.setAddress(publisher.getAddress());
        entity.setWebsite(publisher.getWebsite());
        entity.setEmail(publisher.getEmail());
        entity.setPhoneNumber(publisher.getPhoneNumber());
        entity.setCountry(publisher.getCountry());
        return entity;
    }
    
    public Publisher toDomain() {
        Publisher publisher = new Publisher();
        publisher.setId(UUID.fromString(this.id));
        publisher.setName(this.name);
        publisher.setAddress(this.address);
        publisher.setWebsite(this.website);
        publisher.setEmail(this.email);
        publisher.setPhoneNumber(this.phoneNumber);
        publisher.setCountry(this.country);
        return publisher;
    }
    
    // Getters and Setters
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
}

