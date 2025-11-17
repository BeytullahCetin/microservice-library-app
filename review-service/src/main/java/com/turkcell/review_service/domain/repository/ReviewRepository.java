package com.turkcell.review_service.domain.repository;

import com.turkcell.review_service.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByCustomerId(UUID customerId);
    List<Review> findByBookId(UUID bookId);
    List<Review> findByCustomerIdAndBookId(UUID customerId, UUID bookId);
    boolean existsByCustomerIdAndBookId(UUID customerId, UUID bookId);
}


