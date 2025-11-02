package com.turkcell.book_service.domain.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Author Entity - Domain Model
 */
public class Author {
    private UUID id;
    private String firstName;
    private String lastName;
    private String biography;
    private LocalDate birthDate;
    private String nationality;
    
    public Author() {
        this.id = UUID.randomUUID();
    }
    
    public Author(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Author(String firstName, String lastName, String biography, 
                  LocalDate birthDate, String nationality) {
        this(firstName, lastName);
        this.biography = biography;
        this.birthDate = birthDate;
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
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", fullName='" + getFullName() + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}

