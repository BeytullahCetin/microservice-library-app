package com.turkcell.book_service.infrastructure.persistence.entity;

import com.turkcell.book_service.domain.entities.Translator;
import jakarta.persistence.*;

import java.util.UUID;

/**
 * JPA Entity for Translator persistence
 */
@Entity
@Table(name = "translators")
public class JpaTranslatorEntity {
    
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    
    @Column(name = "biography", length = 2000)
    private String biography;
    
    @Column(name = "nationality", length = 100)
    private String nationality;
    
    // Constructors
    public JpaTranslatorEntity() {
    }
    
    // Conversion methods
    public static JpaTranslatorEntity fromDomain(Translator translator) {
        JpaTranslatorEntity entity = new JpaTranslatorEntity();
        entity.setId(translator.getId().toString());
        entity.setFirstName(translator.getFirstName());
        entity.setLastName(translator.getLastName());
        entity.setBiography(translator.getBiography());
        entity.setNationality(translator.getNationality());
        return entity;
    }
    
    public Translator toDomain() {
        Translator translator = new Translator();
        translator.setId(UUID.fromString(this.id));
        translator.setFirstName(this.firstName);
        translator.setLastName(this.lastName);
        translator.setBiography(this.biography);
        translator.setNationality(this.nationality);
        return translator;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
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
}

