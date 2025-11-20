package com.turkcell.book_service.persistence.translator;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "translators")
@Getter
@Setter
public class TranslatorJpaEntity {
	@Id
	private UUID id;
	private String name;
}
