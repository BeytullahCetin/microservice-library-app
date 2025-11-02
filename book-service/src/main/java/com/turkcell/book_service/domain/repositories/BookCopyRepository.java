package com.turkcell.book_service.domain.repositories;

import com.turkcell.book_service.domain.entities.BookCopy;
import com.turkcell.book_service.domain.entities.BookCopy.CopyStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * BookCopy Repository Interface (Port)
 */
public interface BookCopyRepository {
    
    BookCopy save(BookCopy bookCopy);
    
    Optional<BookCopy> findById(UUID id);
    
    Optional<BookCopy> findByBarcode(String barcode);
    
    List<BookCopy> findAll();
    
    List<BookCopy> findByBookId(UUID bookId);
    
    List<BookCopy> findByStatus(CopyStatus status);
    
    List<BookCopy> findAvailableCopiesByBookId(UUID bookId);
    
    void deleteById(UUID id);
    
    boolean existsByBarcode(String barcode);
    
    long countByBookId(UUID bookId);
    
    long countAvailableByBookId(UUID bookId);
}

