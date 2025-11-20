package com.turkcell.book_service.persistence.translator;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslatorJpaRepository extends JpaRepository<TranslatorJpaEntity, UUID> {

}
