package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.domain.entities.Book;
import com.turkcell.book_service.domain.entities.BookCopy;
import com.turkcell.book_service.domain.entities.BookCopy.CopyStatus;
import com.turkcell.book_service.domain.repositories.BookCopyRepository;
import com.turkcell.book_service.infrastructure.persistence.entity.JpaBookCopyEntity;
import com.turkcell.book_service.infrastructure.persistence.entity.JpaBookEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adapter implementation for BookCopyRepository
 */
@Component
public class BookCopyRepositoryAdapter implements BookCopyRepository {
    
    private final JpaBookCopyRepository jpaBookCopyRepository;
    private final JpaBookRepository jpaBookRepository;
    
    public BookCopyRepositoryAdapter(JpaBookCopyRepository jpaBookCopyRepository,
                                     JpaBookRepository jpaBookRepository) {
        this.jpaBookCopyRepository = jpaBookCopyRepository;
        this.jpaBookRepository = jpaBookRepository;
    }
    
    @Override
    public BookCopy save(BookCopy bookCopy) {
        JpaBookCopyEntity entity = JpaBookCopyEntity.fromDomain(bookCopy);
        
        // Set book reference if present
        if (bookCopy.getBook() != null) {
            String bookId = bookCopy.getBook().getId().toString();
            JpaBookEntity bookEntity = jpaBookRepository.findById(bookId)
                    .orElseThrow(() -> new IllegalArgumentException("Book not found"));
            entity.setBook(bookEntity);
        }
        
        JpaBookCopyEntity saved = jpaBookCopyRepository.save(entity);
        BookCopy result = saved.toDomain();
        
        // Set book reference if available
        if (saved.getBook() != null) {
            Book book = new Book();
            book.setId(UUID.fromString(saved.getBook().getId()));
            book.setTitle(saved.getBook().getTitle());
            book.setIsbn(saved.getBook().getIsbn());
            result.setBook(book);
        }
        
        return result;
    }
    
    @Override
    public Optional<BookCopy> findById(UUID id) {
        return jpaBookCopyRepository.findById(id.toString())
                .map(this::enrichWithBook);
    }
    
    @Override
    public Optional<BookCopy> findByBarcode(String barcode) {
        return jpaBookCopyRepository.findByBarcode(barcode)
                .map(this::enrichWithBook);
    }
    
    @Override
    public List<BookCopy> findAll() {
        return jpaBookCopyRepository.findAll().stream()
                .map(this::enrichWithBook)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<BookCopy> findByBookId(UUID bookId) {
        return jpaBookCopyRepository.findByBookId(bookId.toString()).stream()
                .map(this::enrichWithBook)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<BookCopy> findByStatus(CopyStatus status) {
        return jpaBookCopyRepository.findByStatus(status).stream()
                .map(this::enrichWithBook)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<BookCopy> findAvailableCopiesByBookId(UUID bookId) {
        return jpaBookCopyRepository.findAvailableCopiesByBookId(bookId.toString()).stream()
                .map(this::enrichWithBook)
                .collect(Collectors.toList());
    }
    
    private BookCopy enrichWithBook(JpaBookCopyEntity entity) {
        BookCopy bookCopy = entity.toDomain();
        if (entity.getBook() != null) {
            Book book = new Book();
            book.setId(UUID.fromString(entity.getBook().getId()));
            book.setTitle(entity.getBook().getTitle());
            book.setIsbn(entity.getBook().getIsbn());
            bookCopy.setBook(book);
        }
        return bookCopy;
    }
    
    @Override
    public void deleteById(UUID id) {
        jpaBookCopyRepository.deleteById(id.toString());
    }
    
    @Override
    public boolean existsByBarcode(String barcode) {
        return jpaBookCopyRepository.existsByBarcode(barcode);
    }
    
    @Override
    public long countByBookId(UUID bookId) {
        return jpaBookCopyRepository.countByBookId(bookId.toString());
    }
    
    @Override
    public long countAvailableByBookId(UUID bookId) {
        return jpaBookCopyRepository.countAvailableByBookId(bookId.toString());
    }
}

