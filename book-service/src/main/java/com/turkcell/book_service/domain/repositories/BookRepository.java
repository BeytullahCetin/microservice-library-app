package com.turkcell.book_service.domain.repositories;

import com.turkcell.book_service.domain.entities.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Book Repository Interface (Port)
 * Domain layer repository interface following DDD principles
 * Infrastructure layer will provide implementation (Adapter)
 */
public interface BookRepository {
    
    /**
     * Save or update a book
     */
    Book save(Book book);
    
    /**
     * Find book by ID
     */
    Optional<Book> findById(UUID id);
    
    /**
     * Find book by ISBN
     */
    Optional<Book> findByIsbn(String isbn);
    
    /**
     * Find all books
     */
    List<Book> findAll();
    
    /**
     * Find books by title (case-insensitive partial match)
     */
    List<Book> findByTitleContaining(String title);
    
    /**
     * Find books by author ID
     */
    List<Book> findByAuthorId(UUID authorId);
    
    /**
     * Find books by publisher ID
     */
    List<Book> findByPublisherId(UUID publisherId);
    
    /**
     * Find books by language ID
     */
    List<Book> findByLanguageId(UUID languageId);
    
    /**
     * Find available books (with available copies)
     */
    List<Book> findAvailableBooks();
    
    /**
     * Delete book by ID
     */
    void deleteById(UUID id);
    
    /**
     * Check if book exists by ISBN
     */
    boolean existsByIsbn(String isbn);
    
    /**
     * Count total books
     */
    long count();
}

