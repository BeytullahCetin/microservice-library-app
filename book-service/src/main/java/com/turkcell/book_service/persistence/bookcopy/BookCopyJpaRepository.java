package com.turkcell.book_service.persistence.bookcopy;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyJpaRepository extends JpaRepository<BookCopyJpaEntity, UUID> {

}
