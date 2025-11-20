package com.turkcell.book_service.persistence.language;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageJpaRepository extends JpaRepository<LanguageJpaEntity, UUID> {
}
