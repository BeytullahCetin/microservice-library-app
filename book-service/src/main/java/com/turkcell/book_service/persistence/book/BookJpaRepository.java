package com.turkcell.book_service.persistence.book;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookJpaEntity, UUID> {

}
