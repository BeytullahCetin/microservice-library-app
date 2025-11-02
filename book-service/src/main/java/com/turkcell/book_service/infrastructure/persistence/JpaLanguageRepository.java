package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.infrastructure.persistence.entity.JpaLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA Repository for Language
 */
@Repository
public interface JpaLanguageRepository extends JpaRepository<JpaLanguageEntity, String> {
    
    Optional<JpaLanguageEntity> findByCode(String code);
    
    boolean existsByCode(String code);
}

