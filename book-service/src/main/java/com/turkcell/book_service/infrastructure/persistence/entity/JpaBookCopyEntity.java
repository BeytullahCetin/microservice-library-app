package com.turkcell.book_service.infrastructure.persistence.entity;

import com.turkcell.book_service.domain.entities.BookCopy;
import com.turkcell.book_service.domain.entities.BookCopy.CopyStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA Entity for BookCopy persistence
 */
@Entity
@Table(name = "book_copies")
public class JpaBookCopyEntity {
    
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    
    @Column(name = "barcode", nullable = false, unique = true, length = 50)
    private String barcode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private JpaBookEntity book;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private CopyStatus status;
    
    @Column(name = "location", length = 200)
    private String location;
    
    @Column(name = "acquired_date", nullable = false)
    private LocalDateTime acquiredDate;
    
    @Column(name = "condition", length = 20)
    private String condition;
    
    // Constructors
    public JpaBookCopyEntity() {
    }
    
    // Conversion methods
    public static JpaBookCopyEntity fromDomain(BookCopy bookCopy) {
        JpaBookCopyEntity entity = new JpaBookCopyEntity();
        entity.setId(bookCopy.getId().toString());
        entity.setBarcode(bookCopy.getBarcode());
        entity.setStatus(bookCopy.getStatus());
        entity.setLocation(bookCopy.getLocation());
        entity.setAcquiredDate(bookCopy.getAcquiredDate());
        entity.setCondition(bookCopy.getCondition());
        
        // Note: Book reference should be set by the repository adapter to avoid circular dependency
        
        return entity;
    }
    
    public BookCopy toDomain() {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(UUID.fromString(this.id));
        bookCopy.setBarcode(this.barcode);
        bookCopy.setStatus(this.status);
        bookCopy.setLocation(this.location);
        bookCopy.setAcquiredDate(this.acquiredDate);
        bookCopy.setCondition(this.condition);
        
        // Note: Book reference is set by the repository adapter to avoid circular dependency
        
        return bookCopy;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getBarcode() {
        return barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public JpaBookEntity getBook() {
        return book;
    }
    
    public void setBook(JpaBookEntity book) {
        this.book = book;
    }
    
    public CopyStatus getStatus() {
        return status;
    }
    
    public void setStatus(CopyStatus status) {
        this.status = status;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public LocalDateTime getAcquiredDate() {
        return acquiredDate;
    }
    
    public void setAcquiredDate(LocalDateTime acquiredDate) {
        this.acquiredDate = acquiredDate;
    }
    
    public String getCondition() {
        return condition;
    }
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
}

