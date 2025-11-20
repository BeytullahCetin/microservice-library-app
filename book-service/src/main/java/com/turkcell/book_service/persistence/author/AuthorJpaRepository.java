package com.turkcell.book_service.persistence.author;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorJpaRepository extends JpaRepository<AuthorJpaEntity, UUID> {

}