package com.turkcell.book_service.domain.repositories;

import com.turkcell.book_service.domain.entities.Language;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Language Repository Interface (Port)
 */
public interface LanguageRepository {
    
    Language save(Language language);
    
    Optional<Language> findById(UUID id);
    
    Optional<Language> findByCode(String code);
    
    List<Language> findAll();
    
    void deleteById(UUID id);
    
    boolean existsByCode(String code);
    
    long count();
}

