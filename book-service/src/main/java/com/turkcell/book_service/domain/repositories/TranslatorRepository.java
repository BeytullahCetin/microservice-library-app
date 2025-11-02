package com.turkcell.book_service.domain.repositories;

import com.turkcell.book_service.domain.entities.Translator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Translator Repository Interface (Port)
 */
public interface TranslatorRepository {
    
    Translator save(Translator translator);
    
    Optional<Translator> findById(UUID id);
    
    List<Translator> findAll();
    
    List<Translator> findByLastName(String lastName);
    
    List<Translator> findByFirstNameAndLastName(String firstName, String lastName);
    
    List<Translator> findByNationality(String nationality);
    
    void deleteById(UUID id);
    
    boolean existsById(UUID id);
    
    long count();
}

