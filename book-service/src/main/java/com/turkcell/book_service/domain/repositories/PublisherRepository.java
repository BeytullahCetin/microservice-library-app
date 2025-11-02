package com.turkcell.book_service.domain.repositories;

import com.turkcell.book_service.domain.entities.Publisher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Publisher Repository Interface (Port)
 */
public interface PublisherRepository {
    
    Publisher save(Publisher publisher);
    
    Optional<Publisher> findById(UUID id);
    
    Optional<Publisher> findByName(String name);
    
    List<Publisher> findAll();
    
    List<Publisher> findByCountry(String country);
    
    void deleteById(UUID id);
    
    boolean existsByName(String name);
    
    long count();
}

