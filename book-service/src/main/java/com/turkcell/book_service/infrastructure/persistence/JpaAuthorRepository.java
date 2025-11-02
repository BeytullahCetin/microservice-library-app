package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.infrastructure.persistence.entity.JpaAuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA Repository for Author
 */
@Repository
public interface JpaAuthorRepository extends JpaRepository<JpaAuthorEntity, String> {
    
    List<JpaAuthorEntity> findByLastName(String lastName);
    
    List<JpaAuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);
    
    List<JpaAuthorEntity> findByNationality(String nationality);
}

