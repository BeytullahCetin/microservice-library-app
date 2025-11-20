package com.turkcell.book_service.persistence.language;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "languages")
@Getter
@Setter
public class LanguageJpaEntity {
	@Id
	private UUID id;
	private String name;
}
