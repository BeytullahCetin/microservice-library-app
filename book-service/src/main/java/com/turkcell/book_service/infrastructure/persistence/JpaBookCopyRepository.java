package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.domain.entities.BookCopy.CopyStatus;
import com.turkcell.book_service.infrastructure.persistence.entity.JpaBookCopyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA Repository for BookCopy
 */
@Repository
public interface JpaBookCopyRepository extends JpaRepository<JpaBookCopyEntity, String> {
    
    Optional<JpaBookCopyEntity> findByBarcode(String barcode);
    
    List<JpaBookCopyEntity> findByBookId(String bookId);
    
    List<JpaBookCopyEntity> findByStatus(CopyStatus status);
    
    @Query("SELECT bc FROM JpaBookCopyEntity bc WHERE bc.book.id = :bookId AND bc.status = 'AVAILABLE'")
    List<JpaBookCopyEntity> findAvailableCopiesByBookId(@Param("bookId") String bookId);
    
    boolean existsByBarcode(String barcode);
    
    long countByBookId(String bookId);
    
    @Query("SELECT COUNT(bc) FROM JpaBookCopyEntity bc WHERE bc.book.id = :bookId AND bc.status = 'AVAILABLE'")
    long countAvailableByBookId(@Param("bookId") String bookId);
}

