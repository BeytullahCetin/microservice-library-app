package com.turkcell.book_service.application.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * DTO for updating an existing book
 */
public class UpdateBookRequest {
    
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 500, message = "Title must be between 1 and 500 characters")
    private String title;
    
    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;
    
    @Min(value = 1, message = "Page count must be at least 1")
    @Max(value = 10000, message = "Page count cannot exceed 10000")
    private int pageCount;
    
    @NotNull(message = "Publication date is required")
    @PastOrPresent(message = "Publication date cannot be in the future")
    private LocalDate publicationDate;
    
    @NotNull(message = "Publisher ID is required")
    private UUID publisherId;
    
    @NotNull(message = "Language ID is required")
    private UUID languageId;
    
    @NotEmpty(message = "At least one author is required")
    private List<UUID> authorIds;
    
    private List<UUID> translatorIds;
    
    // Constructors
    public UpdateBookRequest() {
    }
    
    public UpdateBookRequest(String title, String description, int pageCount,
                             LocalDate publicationDate, UUID publisherId, UUID languageId,
                             List<UUID> authorIds, List<UUID> translatorIds) {
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.publicationDate = publicationDate;
        this.publisherId = publisherId;
        this.languageId = languageId;
        this.authorIds = authorIds;
        this.translatorIds = translatorIds;
    }
    
    // Getters and Setters
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
    
    public UUID getPublisherId() {
        return publisherId;
    }
    
    public void setPublisherId(UUID publisherId) {
        this.publisherId = publisherId;
    }
    
    public UUID getLanguageId() {
        return languageId;
    }
    
    public void setLanguageId(UUID languageId) {
        this.languageId = languageId;
    }
    
    public List<UUID> getAuthorIds() {
        return authorIds;
    }
    
    public void setAuthorIds(List<UUID> authorIds) {
        this.authorIds = authorIds;
    }
    
    public List<UUID> getTranslatorIds() {
        return translatorIds;
    }
    
    public void setTranslatorIds(List<UUID> translatorIds) {
        this.translatorIds = translatorIds;
    }
}

