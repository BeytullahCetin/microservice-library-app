package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.infrastructure.persistence.entity.JpaPublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA Repository for Publisher
 */
@Repository
public interface JpaPublisherRepository extends JpaRepository<JpaPublisherEntity, String> {
    
    Optional<JpaPublisherEntity> findByName(String name);
    
    List<JpaPublisherEntity> findByCountry(String country);
    
    boolean existsByName(String name);
}

