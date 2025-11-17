package com.turkcell.review_service.application.service;

import com.turkcell.review_service.application.dto.CreateReviewRequest;
import com.turkcell.review_service.application.dto.ReviewResponse;
import com.turkcell.review_service.application.dto.UpdateReviewRequest;
import com.turkcell.review_service.application.exception.BusinessRuleException;
import com.turkcell.review_service.application.exception.ResourceNotFoundException;
import com.turkcell.review_service.domain.entity.Review;
import com.turkcell.review_service.domain.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ReviewApplicationService {

    private final ReviewRepository reviewRepository;

    public ReviewApplicationService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewResponse createReview(CreateReviewRequest request) {
        if (reviewRepository.existsByCustomerIdAndBookId(request.getCustomerId(), request.getBookId())) {
            throw new BusinessRuleException("Customer already submitted a review for this book");
        }
        Review review = new Review(request.getReview(), request.getRating(), request.getCustomerId(), request.getBookId());
        Review saved = reviewRepository.save(review);
        return ReviewResponse.fromDomain(saved);
    }

    public ReviewResponse updateReview(UUID id, UpdateReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        review.updateDetails(request.getReview(), request.getRating());
        Review saved = reviewRepository.save(review);
        return ReviewResponse.fromDomain(saved);
    }

    @Transactional(readOnly = true)
    public ReviewResponse getReview(UUID id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        return ReviewResponse.fromDomain(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviews(UUID customerId, UUID bookId) {
        List<Review> reviews;
        if (customerId != null && bookId != null) {
            reviews = reviewRepository.findByCustomerIdAndBookId(customerId, bookId);
        } else if (customerId != null) {
            reviews = reviewRepository.findByCustomerId(customerId);
        } else if (bookId != null) {
            reviews = reviewRepository.findByBookId(bookId);
        } else {
            reviews = reviewRepository.findAll();
        }
        return reviews.stream()
                .map(ReviewResponse::fromDomain)
                .toList();
    }

    public void deleteReview(UUID id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found");
        }
        reviewRepository.deleteById(id);
    }
}


