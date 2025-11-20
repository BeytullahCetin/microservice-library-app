package com.turkcell.book_service.domain.author.model;

public class Author {
	private final AuthorId id;
	private String name;

	private Author(AuthorId id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Author create(String name) {
		validateName(name);
		return new Author(AuthorId.generate(), name);
	}

	public static Author rehydrate(AuthorId id, String name) {
		return new Author(id, name);
	}

	private static void validateName(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name cannot be null or empty");

		if (name.length() >= 255)
			throw new IllegalArgumentException("Name length must be less than 255 characters");
	}

	public void rename(String name) {
		validateName(name);
		this.name = name;
	}

	public AuthorId getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}