package com.turkcell.review_service.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Review aggregate that keeps track of customer feedback for a book.
 * Contains basic validation logic for rating boundaries and review text.
 */
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "review_text", nullable = false, length = 2000)
    private String reviewText;

    @Column(nullable = false)
    private int rating;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "book_id", nullable = false)
    private UUID bookId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected Review() {
        // for JPA
    }

    public Review(String reviewText, int rating, UUID customerId, UUID bookId) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.customerId = customerId;
        this.bookId = bookId;
        validate();
    }

    public void updateDetails(String reviewText, Integer rating) {
        if (reviewText != null) {
            this.reviewText = reviewText;
        }
        if (rating != null) {
            this.rating = rating;
        }
        validate();
    }

    private void validate() {
        if (reviewText == null || reviewText.isBlank()) {
            throw new IllegalArgumentException("Review text cannot be empty");
        }
        if (reviewText.length() > 2000) {
            throw new IllegalArgumentException("Review text cannot exceed 2000 characters");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        Objects.requireNonNull(customerId, "Customer id cannot be null");
        Objects.requireNonNull(bookId, "Book id cannot be null");
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}


