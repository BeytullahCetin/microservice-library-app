package com.turkcell.book_service.domain.language.model;

public class Language {
	private final LanguageId id;
	private String name;

	private Language(LanguageId id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Language create(String name) {
		validateName(name);

		return new Language(LanguageId.generate(), name);
	}

	public static Language rehydrate(LanguageId id, String name) {
		return new Language(id, name);
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

	public LanguageId getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
