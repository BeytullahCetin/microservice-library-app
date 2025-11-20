package com.turkcell.book_service.persistence.publisher;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherJpaRepository extends JpaRepository<PublisherJpaEntity, UUID> {

}
