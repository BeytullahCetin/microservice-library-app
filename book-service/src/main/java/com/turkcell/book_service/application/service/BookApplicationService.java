package com.turkcell.book_service.application.service;

import com.turkcell.book_service.application.dto.BookResponse;
import com.turkcell.book_service.application.dto.CreateBookRequest;
import com.turkcell.book_service.application.dto.UpdateBookRequest;
import com.turkcell.book_service.domain.entities.Author;
import com.turkcell.book_service.domain.entities.Book;
import com.turkcell.book_service.domain.repositories.BookRepository;
import com.turkcell.book_service.domain.valueobjects.Isbn;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookApplicationService {

    private final BookRepository bookRepository;

    public BookApplicationService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookResponse create(CreateBookRequest request) {
        List<Author> authors = request.getAuthors().stream()
            .map(a -> new Author(a.getFirstName(), a.getLastName()))
            .collect(Collectors.toCollection(ArrayList::new));

        Book book = new Book(
            request.getTitle(),
            new Isbn(request.getIsbn()),
            request.getPublicationYear(),
            authors
        );

        Book saved = bookRepository.save(book);
        return toResponse(saved);
    }

    @Transactional
    public BookResponse update(Long id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Book not found: " + id));

        book.setTitle(request.getTitle());
        book.setPublicationYear(request.getPublicationYear());

        if (request.getAuthors() != null) {
            // naive replace list
            List<Author> newAuthors = request.getAuthors().stream()
                .map(a -> new Author(a.getFirstName(), a.getLastName()))
                .collect(Collectors.toCollection(ArrayList::new));
            book.getAuthors().clear();
            book.getAuthors().addAll(newAuthors);
        }

        Book saved = bookRepository.save(book);
        return toResponse(saved);
    }

    public BookResponse getById(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Book not found: " + id));
        return toResponse(book);
    }

    public List<BookResponse> list() {
        return bookRepository.findAll().stream().map(this::toResponse).toList();
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    private BookResponse toResponse(Book book) {
        BookResponse resp = new BookResponse();
        resp.setId(book.getId());
        resp.setTitle(book.getTitle());
        resp.setIsbn(book.getIsbn().getValue());
        resp.setPublicationYear(book.getPublicationYear());
        List<BookResponse.Author> authors = book.getAuthors().stream().map(a -> {
            BookResponse.Author ra = new BookResponse.Author();
            ra.setId(a.getId());
            ra.setFirstName(a.getFirstName());
            ra.setLastName(a.getLastName());
            return ra;
        }).toList();
        resp.setAuthors(authors);
        return resp;
    }
}


