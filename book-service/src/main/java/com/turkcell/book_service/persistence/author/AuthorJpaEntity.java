package com.turkcell.book_service.persistence.author;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class AuthorJpaEntity {
	@Id
	private UUID id;

	@Column(nullable = false, length = 500)
	private String name;
}