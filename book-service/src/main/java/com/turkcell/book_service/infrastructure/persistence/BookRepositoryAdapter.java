package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.domain.entities.Book;
import com.turkcell.book_service.domain.repositories.BookRepository;
import com.turkcell.book_service.domain.valueobjects.Isbn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryAdapter implements BookRepository {

    private final JpaBookRepository jpaBookRepository;

    public BookRepositoryAdapter(JpaBookRepository jpaBookRepository) {
        this.jpaBookRepository = jpaBookRepository;
    }

    @Override
    public Book save(Book book) {
        return jpaBookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jpaBookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByIsbn(Isbn isbn) {
        return jpaBookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> findAll() {
        return jpaBookRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaBookRepository.deleteById(id);
    }
}


