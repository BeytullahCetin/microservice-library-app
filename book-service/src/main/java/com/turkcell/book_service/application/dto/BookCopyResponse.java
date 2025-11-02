package com.turkcell.book_service.application.dto;

import com.turkcell.book_service.domain.entities.BookCopy;
import com.turkcell.book_service.domain.entities.BookCopy.CopyStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for book copy responses
 */
public class BookCopyResponse {
    private UUID id;
    private String barcode;
    private UUID bookId;
    private String bookTitle;
    private CopyStatus status;
    private String location;
    private LocalDateTime acquiredDate;
    private String condition;
    
    // Constructors
    public BookCopyResponse() {
    }
    
    // Factory method to create from domain entity
    public static BookCopyResponse fromDomain(BookCopy bookCopy) {
        BookCopyResponse response = new BookCopyResponse();
        response.setId(bookCopy.getId());
        response.setBarcode(bookCopy.getBarcode());
        response.setStatus(bookCopy.getStatus());
        response.setLocation(bookCopy.getLocation());
        response.setAcquiredDate(bookCopy.getAcquiredDate());
        response.setCondition(bookCopy.getCondition());
        
        if (bookCopy.getBook() != null) {
            response.setBookId(bookCopy.getBook().getId());
            response.setBookTitle(bookCopy.getBook().getTitle());
        }
        
        return response;
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
    
    public UUID getBookId() {
        return bookId;
    }
    
    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }
    
    public String getBookTitle() {
        return bookTitle;
    }
    
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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

