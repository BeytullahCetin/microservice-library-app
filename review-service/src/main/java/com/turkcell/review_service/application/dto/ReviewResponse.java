package com.turkcell.review_service.application.dto;

import com.turkcell.review_service.domain.entity.Review;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewResponse {

    private UUID id;
    private String review;
    private int rating;
    private UUID customerId;
    private UUID bookId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReviewResponse fromDomain(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.id = review.getId();
        response.review = review.getReviewText();
        response.rating = review.getRating();
        response.customerId = review.getCustomerId();
        response.bookId = review.getBookId();
        response.createdAt = review.getCreatedAt();
        response.updatedAt = review.getUpdatedAt();
        return response;
    }

    public UUID getId() {
        return id;
    }

    public String getReview() {
        return review;
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


