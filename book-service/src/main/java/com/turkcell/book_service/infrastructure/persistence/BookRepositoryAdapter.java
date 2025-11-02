package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.domain.entities.Author;
import com.turkcell.book_service.domain.entities.Book;
import com.turkcell.book_service.domain.repositories.BookRepository;
import com.turkcell.book_service.domain.valueobjects.Isbn;
import com.turkcell.book_service.infrastructure.persistence.entity.JpaAuthorEntity;
import com.turkcell.book_service.infrastructure.persistence.entity.JpaBookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryAdapter implements BookRepository {

    private final JpaBookRepository jpaBookRepository;

    public BookRepositoryAdapter(JpaBookRepository jpaBookRepository) {
        this.jpaBookRepository = jpaBookRepository;
    }

    @Override
    public Book save(Book book) {
        JpaBookEntity entity = toEntity(book);
        JpaBookEntity saved = jpaBookRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jpaBookRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Book> findByIsbn(Isbn isbn) {
        return jpaBookRepository.findByIsbn(isbn.getValue()).map(this::toDomain);
    }

    @Override
    public List<Book> findAll() {
        return jpaBookRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaBookRepository.deleteById(id);
    }

    private JpaBookEntity toEntity(Book book) {
        JpaBookEntity e = new JpaBookEntity();
        e.setId(book.getId());
        e.setTitle(book.getTitle());
        e.setIsbn(book.getIsbn().getValue());
        e.setPublicationYear(book.getPublicationYear());
        List<JpaAuthorEntity> authors = book.getAuthors().stream().map(a -> {
            JpaAuthorEntity ja = new JpaAuthorEntity();
            ja.setId(a.getId());
            ja.setFirstName(a.getFirstName());
            ja.setLastName(a.getLastName());
            return ja;
        }).collect(Collectors.toList());
        e.setAuthors(authors);
        e.setCreatedAt(book.getCreatedAt());
        return e;
    }

    private Book toDomain(JpaBookEntity e) {
        List<Author> authors = e.getAuthors().stream().map(ja -> {
            Author a = new Author(ja.getFirstName(), ja.getLastName());
            a.setId(ja.getId());
            return a;
        }).collect(Collectors.toList());
        Book b = new Book(e.getTitle(), new Isbn(e.getIsbn()), e.getPublicationYear(), authors);
        b.setId(e.getId());
        return b;
    }
}


