package com.turkcell.book_service.domain.entities;

import com.turkcell.book_service.domain.valueobjects.Isbn;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

// Domain model is persistence-ignorant in onion architecture.
public class Book {

    private Long id;
    private String title;
    private Isbn isbn;
    private int publicationYear;
    private List<Author> authors = new ArrayList<>();
    private Instant createdAt = Instant.now();

    public Book() {
    }

    public Book(String title, Isbn isbn, int publicationYear, List<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        if (authors != null) {
            this.authors.addAll(authors);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public void setIsbn(Isbn isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        if (author != null && !this.authors.contains(author)) {
            this.authors.add(author);
        }
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
