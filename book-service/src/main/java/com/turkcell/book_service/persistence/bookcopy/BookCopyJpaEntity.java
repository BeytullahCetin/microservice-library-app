package com.turkcell.book_service.persistence.bookcopy;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book_copies")
@Getter
@Setter
public class BookCopyJpaEntity {
	@Id
	private UUID id;

	private UUID bookId;
	private LocalDateTime acquisitionDate;
	private String status;
}
