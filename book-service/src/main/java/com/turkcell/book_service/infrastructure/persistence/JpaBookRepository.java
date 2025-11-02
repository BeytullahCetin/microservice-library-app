package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.domain.entities.Book;
import com.turkcell.book_service.domain.valueobjects.Isbn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaBookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(Isbn isbn);
}


