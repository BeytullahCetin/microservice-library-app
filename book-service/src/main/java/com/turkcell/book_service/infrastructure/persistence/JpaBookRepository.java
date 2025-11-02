package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.infrastructure.persistence.entity.JpaBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaBookRepository extends JpaRepository<JpaBookEntity, Long> {
    Optional<JpaBookEntity> findByIsbn(String isbn);
}


