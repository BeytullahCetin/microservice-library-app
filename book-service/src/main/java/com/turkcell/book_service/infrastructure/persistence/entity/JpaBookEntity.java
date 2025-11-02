package com.turkcell.book_service.infrastructure.persistence.entity;

import com.turkcell.book_service.domain.entities.Book;
import com.turkcell.book_service.domain.entities.BookCopy;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JPA Entity for Book persistence
 * Infrastructure layer - mapping between domain and database
 */
@Entity
@Table(name = "books")
public class JpaBookEntity {
    
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "isbn", unique = true, nullable = false, length = 20)
    private String isbn;
    
    @Column(name = "title", nullable = false, length = 500)
    private String title;
    
    @Column(name = "description", length = 2000)
    private String description;
    
    @Column(name = "page_count", nullable = false)
    private int pageCount;
    
    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private JpaPublisherEntity publisher;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private JpaLanguageEntity language;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<JpaAuthorEntity> authors = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_translators",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id")
    )
    private List<JpaTranslatorEntity> translators = new ArrayList<>();
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JpaBookCopyEntity> copies = new ArrayList<>();
    
    @Column(name = "available")
    private boolean available;
    
    // Constructors
    public JpaBookEntity() {
    }
    
    // Conversion methods
    public static JpaBookEntity fromDomain(Book book) {
        JpaBookEntity entity = new JpaBookEntity();
        entity.setId(book.getId().toString());
        entity.setIsbn(book.getIsbn());
        entity.setTitle(book.getTitle());
        entity.setDescription(book.getDescription());
        entity.setPageCount(book.getPageCount());
        entity.setPublicationDate(book.getPublicationDate());
        entity.setAvailable(book.isAvailable());
        
        // Note: Related entities (publisher, language, authors, translators) 
        // should be set by the repository adapter using managed entities
        
        return entity;
    }
    
    public Book toDomain() {
        Book book = new Book();
        book.setId(UUID.fromString(this.id));
        book.setIsbn(this.isbn);
        book.setTitle(this.title);
        book.setDescription(this.description);
        book.setPageCount(this.pageCount);
        book.setPublicationDate(this.publicationDate);
        
        if (this.publisher != null) {
            book.setPublisher(this.publisher.toDomain());
        }
        
        if (this.language != null) {
            book.setLanguage(this.language.toDomain());
        }
        
        book.setAuthors(this.authors.stream()
                .map(JpaAuthorEntity::toDomain)
                .collect(Collectors.toList()));
        
        book.setTranslators(this.translators.stream()
                .map(JpaTranslatorEntity::toDomain)
                .collect(Collectors.toList()));
        
        // Convert copies without setting book reference to avoid circular dependency
        book.setCopies(this.copies.stream()
                .map(copy -> {
                    BookCopy bookCopy = new BookCopy();
                    bookCopy.setId(UUID.fromString(copy.getId()));
                    bookCopy.setBarcode(copy.getBarcode());
                    bookCopy.setStatus(copy.getStatus());
                    bookCopy.setLocation(copy.getLocation());
                    bookCopy.setAcquiredDate(copy.getAcquiredDate());
                    bookCopy.setCondition(copy.getCondition());
                    return bookCopy;
                })
                .collect(Collectors.toList()));
        
        return book;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
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
        this.pageCount = pageCount;
    }
    
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
    
    public JpaPublisherEntity getPublisher() {
        return publisher;
    }
    
    public void setPublisher(JpaPublisherEntity publisher) {
        this.publisher = publisher;
    }
    
    public JpaLanguageEntity getLanguage() {
        return language;
    }
    
    public void setLanguage(JpaLanguageEntity language) {
        this.language = language;
    }
    
    public List<JpaAuthorEntity> getAuthors() {
        return authors;
    }
    
    public void setAuthors(List<JpaAuthorEntity> authors) {
        this.authors = authors;
    }
    
    public List<JpaTranslatorEntity> getTranslators() {
        return translators;
    }
    
    public void setTranslators(List<JpaTranslatorEntity> translators) {
        this.translators = translators;
    }
    
    public List<JpaBookCopyEntity> getCopies() {
        return copies;
    }
    
    public void setCopies(List<JpaBookCopyEntity> copies) {
        this.copies = copies;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
}

