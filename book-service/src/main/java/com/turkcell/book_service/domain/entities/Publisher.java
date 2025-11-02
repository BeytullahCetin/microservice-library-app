package com.turkcell.book_service.domain.entities;

import java.util.Objects;
import java.util.UUID;

/**
 * Publisher Entity - Domain Model
 */
public class Publisher {
    private UUID id;
    private String name;
    private String address;
    private String website;
    private String email;
    private String phoneNumber;
    private String country;
    
    public Publisher() {
        this.id = UUID.randomUUID();
    }
    
    public Publisher(String name) {
        this();
        this.name = name;
    }
    
    public Publisher(String name, String address, String website, 
                     String email, String phoneNumber, String country) {
        this(name);
        this.address = address;
        this.website = website;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
    }
    
    // Business Logic
    public void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Publisher name cannot be empty");
        }
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher)) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(id, publisher.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
