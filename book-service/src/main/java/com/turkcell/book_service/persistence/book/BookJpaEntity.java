package com.turkcell.book_service.persistence.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BookJpaEntity {

	@Id
	private UUID id;

	@Column(nullable = false, length = 500)
	private String title;

	@Column(nullable = false, unique = true, length = 13)
	private String isbn;

	@Column(nullable = false)
	private Integer pageCount;

	@Column(nullable = false)
	private LocalDate publishDate;

	@Column(name = "publisher_id", nullable = false, columnDefinition = "UUID")
	private UUID publisherId;

	@Column(name = "language_id", nullable = false, columnDefinition = "UUID")
	private UUID languageId;

	@ElementCollection
	@CollectionTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"))
	@Column(name = "author_id", columnDefinition = "UUID")
	private List<UUID> authorIds = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "book_translators", joinColumns = @JoinColumn(name = "book_id"))
	@Column(name = "translator_id", columnDefinition = "UUID")
	private List<UUID> translatorIds = new ArrayList<>();
}