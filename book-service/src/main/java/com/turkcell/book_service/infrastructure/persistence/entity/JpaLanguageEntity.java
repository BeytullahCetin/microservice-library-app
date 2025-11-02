package com.turkcell.book_service.infrastructure.persistence.entity;

import com.turkcell.book_service.domain.entities.Language;
import jakarta.persistence.*;

import java.util.UUID;

/**
 * JPA Entity for Language persistence
 */
@Entity
@Table(name = "languages")
public class JpaLanguageEntity {
    
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "code", nullable = false, unique = true, length = 2)
    private String code;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "native_name", length = 100)
    private String nativeName;
    
    // Constructors
    public JpaLanguageEntity() {
    }
    
    // Conversion methods
    public static JpaLanguageEntity fromDomain(Language language) {
        JpaLanguageEntity entity = new JpaLanguageEntity();
        entity.setId(language.getId().toString());
        entity.setCode(language.getCode());
        entity.setName(language.getName());
        entity.setNativeName(language.getNativeName());
        return entity;
    }
    
    public Language toDomain() {
        Language language = new Language();
        language.setId(UUID.fromString(this.id));
        language.setCode(this.code);
        language.setName(this.name);
        language.setNativeName(this.nativeName);
        return language;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
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
}

