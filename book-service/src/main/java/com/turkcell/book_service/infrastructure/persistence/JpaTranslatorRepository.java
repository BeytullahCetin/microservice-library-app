package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.infrastructure.persistence.entity.JpaTranslatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA Repository for Translator
 */
@Repository
public interface JpaTranslatorRepository extends JpaRepository<JpaTranslatorEntity, String> {
    
    List<JpaTranslatorEntity> findByLastName(String lastName);
    
    List<JpaTranslatorEntity> findByFirstNameAndLastName(String firstName, String lastName);
    
    List<JpaTranslatorEntity> findByNationality(String nationality);
}

