package com.turkcell.book_service.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 * DTO for creating a new book copy
 */
public class CreateBookCopyRequest {
    
    @NotBlank(message = "Barcode is required")
    private String barcode;
    
    @NotNull(message = "Book ID is required")
    private UUID bookId;
    
    private String location;
    
    private String condition = "NEW";
    
    // Constructors
    public CreateBookCopyRequest() {
    }
    
    public CreateBookCopyRequest(String barcode, UUID bookId, String location, String condition) {
        this.barcode = barcode;
        this.bookId = bookId;
        this.location = location;
        this.condition = condition;
    }
    
    // Getters and Setters
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
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getCondition() {
        return condition;
    }
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
}

