package com.turkcell.book_service.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * BookCopy Entity - Domain Model
 * Represents a physical copy of a book
 */
public class BookCopy {
    private UUID id;
    private String barcode; // Unique identifier for physical copy
    private Book book;
    private CopyStatus status;
    private String location; // Physical location in library
    private LocalDateTime acquiredDate;
    private String condition; // NEW, GOOD, FAIR, POOR
    
    public BookCopy() {
        this.id = UUID.randomUUID();
        this.status = CopyStatus.AVAILABLE;
        this.acquiredDate = LocalDateTime.now();
        this.condition = "NEW";
    }
    
    public BookCopy(String barcode, Book book) {
        this();
        this.barcode = barcode;
        this.book = book;
    }
    
    public BookCopy(String barcode, Book book, String location) {
        this(barcode, book);
        this.location = location;
    }
    
    // Business Logic
    public boolean isAvailable() {
        return status == CopyStatus.AVAILABLE;
    }
    
    public void borrow() {
        if (status != CopyStatus.AVAILABLE) {
            throw new IllegalStateException("Book copy is not available for borrowing");
        }
        this.status = CopyStatus.BORROWED;
    }
    
    public void returnCopy() {
        if (status != CopyStatus.BORROWED) {
            throw new IllegalStateException("Book copy is not borrowed");
        }
        this.status = CopyStatus.AVAILABLE;
    }
    
    public void markAsLost() {
        this.status = CopyStatus.LOST;
    }
    
    public void markAsDamaged() {
        this.status = CopyStatus.DAMAGED;
    }
    
    public void markAsReserved() {
        if (status != CopyStatus.AVAILABLE) {
            throw new IllegalStateException("Book copy is not available for reservation");
        }
        this.status = CopyStatus.RESERVED;
    }
    
    public void updateCondition(String condition) {
        if (condition == null || 
            (!condition.equals("NEW") && !condition.equals("GOOD") && 
             !condition.equals("FAIR") && !condition.equals("POOR"))) {
            throw new IllegalArgumentException("Invalid condition. Must be: NEW, GOOD, FAIR, or POOR");
        }
        this.condition = condition;
    }
    
    public void validate() {
        if (barcode == null || barcode.trim().isEmpty()) {
            throw new IllegalStateException("Barcode cannot be empty");
        }
        if (book == null) {
            throw new IllegalStateException("Book reference cannot be null");
        }
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getBarcode() {
        return barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public Book getBook() {
        return book;
    }
    
    public void setBook(Book book) {
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookCopy)) return false;
        BookCopy bookCopy = (BookCopy) o;
        return Objects.equals(id, bookCopy.id) && Objects.equals(barcode, bookCopy.barcode);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, barcode);
    }
    
    @Override
    public String toString() {
        return "BookCopy{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", status=" + status +
                ", location='" + location + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
    
    // Inner Enum
    public enum CopyStatus {
        AVAILABLE,
        BORROWED,
        RESERVED,
        LOST,
        DAMAGED,
        MAINTENANCE
    }
}
