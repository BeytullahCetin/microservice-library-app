package com.turkcell.book_service.domain.entities;

import java.util.Objects;
import java.util.UUID;

/**
 * Translator Entity - Domain Model
 */
public class Translator {
    private UUID id;
    private String firstName;
    private String lastName;
    private String biography;
    private String nationality;
    
    public Translator() {
        this.id = UUID.randomUUID();
    }
    
    public Translator(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Translator(String firstName, String lastName, String biography, String nationality) {
        this(firstName, lastName);
        this.biography = biography;
        this.nationality = nationality;
    }
    
    // Business Logic
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public void validate() {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalStateException("First name cannot be empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalStateException("Last name cannot be empty");
        }
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getBiography() {
        return biography;
    }
    
    public void setBiography(String biography) {
        this.biography = biography;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Translator)) return false;
        Translator that = (Translator) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Translator{" +
                "id=" + id +
                ", fullName='" + getFullName() + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
