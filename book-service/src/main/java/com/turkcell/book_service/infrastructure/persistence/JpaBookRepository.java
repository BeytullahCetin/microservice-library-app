package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.infrastructure.persistence.entity.JpaBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA Repository for Book
 */
@Repository
public interface JpaBookRepository extends JpaRepository<JpaBookEntity, String> {
    
    Optional<JpaBookEntity> findByIsbn(String isbn);
    
    List<JpaBookEntity> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT b FROM JpaBookEntity b JOIN b.authors a WHERE a.id = :authorId")
    List<JpaBookEntity> findByAuthorId(@Param("authorId") String authorId);
    
    @Query("SELECT b FROM JpaBookEntity b WHERE b.publisher.id = :publisherId")
    List<JpaBookEntity> findByPublisherId(@Param("publisherId") String publisherId);
    
    @Query("SELECT b FROM JpaBookEntity b WHERE b.language.id = :languageId")
    List<JpaBookEntity> findByLanguageId(@Param("languageId") String languageId);
    
    @Query("SELECT b FROM JpaBookEntity b WHERE b.available = true")
    List<JpaBookEntity> findAvailableBooks();
    
    boolean existsByIsbn(String isbn);
}

