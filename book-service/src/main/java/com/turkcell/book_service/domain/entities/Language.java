package com.turkcell.book_service.domain.entities;

import java.util.Objects;
import java.util.UUID;

/**
 * Language Entity - Domain Model
 */
public class Language {
    private UUID id;
    private String code; // ISO 639-1 code (e.g., "en", "tr", "de")
    private String name; // Full name (e.g., "English", "Turkish", "German")
    private String nativeName; // Native name (e.g., "English", "Türkçe", "Deutsch")
    
    public Language() {
        this.id = UUID.randomUUID();
    }
    
    public Language(String code, String name) {
        this();
        this.code = code;
        this.name = name;
    }
    
    public Language(String code, String name, String nativeName) {
        this(code, name);
        this.nativeName = nativeName;
    }
    
    // Business Logic
    public void validate() {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalStateException("Language code cannot be empty");
        }
        if (code.length() != 2) {
            throw new IllegalStateException("Language code must be 2 characters (ISO 639-1)");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Language name cannot be empty");
        }
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getNativeName() {
        return nativeName;
    }
    
    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language)) return false;
        Language language = (Language) o;
        return Objects.equals(id, language.id) && Objects.equals(code, language.code);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
    
    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", nativeName='" + nativeName + '\'' +
                '}';
    }
}
