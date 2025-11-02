package com.turkcell.book_service.domain.repositories;

import com.turkcell.book_service.domain.entities.Author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Author Repository Interface (Port)
 */
public interface AuthorRepository {
    
    Author save(Author author);
    
    Optional<Author> findById(UUID id);
    
    List<Author> findAll();
    
    List<Author> findByLastName(String lastName);
    
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
    
    List<Author> findByNationality(String nationality);
    
    void deleteById(UUID id);
    
    boolean existsById(UUID id);
    
    long count();
}

