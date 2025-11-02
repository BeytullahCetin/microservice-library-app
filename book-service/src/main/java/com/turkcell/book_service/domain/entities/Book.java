package com.turkcell.book_service.domain.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Book Aggregate Root - Domain Entity
 * Rich domain model with business logic
 */
public class Book {
    private UUID id;
    private String isbn;
    private String title;
    private String description;
    private int pageCount;
    private LocalDate publicationDate;
    private Publisher publisher;
    private Language language;
    private List<Author> authors;
    private List<Translator> translators;
    private List<BookCopy> copies;
    
    // Domain state
    private boolean available;
    
    // Constructor
    public Book() {
        this.id = UUID.randomUUID();
        this.authors = new ArrayList<>();
        this.translators = new ArrayList<>();
        this.copies = new ArrayList<>();
        this.available = false;
    }
    
    public Book(String isbn, String title, String description, int pageCount, 
                LocalDate publicationDate, Publisher publisher, Language language) {
        this();
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.language = language;
    }
    
    // Domain Business Logic
    public void addAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        if (!this.authors.contains(author)) {
            this.authors.add(author);
        }
    }
    
    public void removeAuthor(Author author) {
        this.authors.remove(author);
    }
    
    public void addTranslator(Translator translator) {
        if (translator == null) {
            throw new IllegalArgumentException("Translator cannot be null");
        }
        if (!this.translators.contains(translator)) {
            this.translators.add(translator);
        }
    }
    
    public void removeTranslator(Translator translator) {
        this.translators.remove(translator);
    }
    
    public void addCopy(BookCopy copy) {
        if (copy == null) {
            throw new IllegalArgumentException("BookCopy cannot be null");
        }
        this.copies.add(copy);
        updateAvailability();
    }
    
    public void removeCopy(BookCopy copy) {
        this.copies.remove(copy);
        updateAvailability();
    }
    
    public int getAvailableCopiesCount() {
        return (int) copies.stream()
                .filter(BookCopy::isAvailable)
                .count();
    }
    
    public int getTotalCopiesCount() {
        return copies.size();
    }
    
    private void updateAvailability() {
        this.available = getAvailableCopiesCount() > 0;
    }
    
    public void validateIsbn() {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalStateException("ISBN cannot be empty");
        }
        // ISBN-10 or ISBN-13 validation
        String cleanIsbn = isbn.replaceAll("[^0-9X]", "");
        if (cleanIsbn.length() != 10 && cleanIsbn.length() != 13) {
            throw new IllegalStateException("ISBN must be 10 or 13 digits");
        }
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
        validateIsbn();
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getPageCount() {
        return pageCount;
    }
    
    public void setPageCount(int pageCount) {
        if (pageCount <= 0) {
            throw new IllegalArgumentException("Page count must be positive");
        }
        this.pageCount = pageCount;
    }
    
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
    
    public Publisher getPublisher() {
        return publisher;
    }
    
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    public Language getLanguage() {
        return language;
    }
    
    public void setLanguage(Language language) {
        this.language = language;
    }
    
    public List<Author> getAuthors() {
        return new ArrayList<>(authors);
    }
    
    public void setAuthors(List<Author> authors) {
        this.authors = authors != null ? authors : new ArrayList<>();
    }
    
    public List<Translator> getTranslators() {
        return new ArrayList<>(translators);
    }
    
    public void setTranslators(List<Translator> translators) {
        this.translators = translators != null ? translators : new ArrayList<>();
    }
    
    public List<BookCopy> getCopies() {
        return new ArrayList<>(copies);
    }
    
    public void setCopies(List<BookCopy> copies) {
        this.copies = copies != null ? copies : new ArrayList<>();
        updateAvailability();
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(isbn, book.isbn);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", available=" + available +
                ", copiesCount=" + copies.size() +
                '}';
    }
}

