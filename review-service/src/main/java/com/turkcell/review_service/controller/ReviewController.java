package com.turkcell.review_service.controller;

import com.turkcell.review_service.application.dto.CreateReviewRequest;
import com.turkcell.review_service.application.dto.ReviewResponse;
import com.turkcell.review_service.application.dto.UpdateReviewRequest;
import com.turkcell.review_service.application.service.ReviewApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewApplicationService reviewApplicationService;

    public ReviewController(ReviewApplicationService reviewApplicationService) {
        this.reviewApplicationService = reviewApplicationService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@Valid @RequestBody CreateReviewRequest request) {
        ReviewResponse response = reviewApplicationService.createReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable UUID id,
                                                       @Valid @RequestBody UpdateReviewRequest request) {
        ReviewResponse response = reviewApplicationService.updateReview(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable UUID id) {
        return ResponseEntity.ok(reviewApplicationService.getReview(id));
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> listReviews(
            @RequestParam(required = false) UUID customerId,
            @RequestParam(required = false) UUID bookId) {
        return ResponseEntity.ok(reviewApplicationService.getReviews(customerId, bookId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        reviewApplicationService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}


