package com.turkcell.book_service.domain.repositories;

import com.turkcell.book_service.domain.entities.Book;
import com.turkcell.book_service.domain.valueobjects.Isbn;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(Long id);
    Optional<Book> findByIsbn(Isbn isbn);
    List<Book> findAll();
    void deleteById(Long id);
}


