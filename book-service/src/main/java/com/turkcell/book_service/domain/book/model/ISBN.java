package com.turkcell.book_service.domain.book.model;

import java.io.Serializable;
import java.util.Objects;

public record ISBN(String value) implements Serializable {
	public ISBN {
		Objects.requireNonNull(value, "Value for ISBN cannot be null!");

		if (value.trim().isEmpty()) {
			throw new IllegalArgumentException("ISBN cannot be null or empty");
		}

		String cleaned = value.replaceAll("[^0-9X]", "");

		if (!isValidISBN10(cleaned) && !isValidISBN13(cleaned)) {
			throw new IllegalArgumentException("Invalid ISBN format: " + value);
		}
	}

	private static boolean isValidISBN10(String isbn) {
		if (isbn.length() != 10)
			return false;

		int sum = 0;
		for (int i = 0; i < 9; i++) {
			if (!Character.isDigit(isbn.charAt(i)))
				return false;
			sum += (isbn.charAt(i) - '0') * (10 - i);
		}

		char lastChar = isbn.charAt(9);
		sum += (lastChar == 'X') ? 10 : (lastChar - '0');

		return sum % 11 == 0;
	}

	private static boolean isValidISBN13(String isbn) {
		if (isbn.length() != 13)
			return false;

		int sum = 0;
		for (int i = 0; i < 13; i++) {
			if (!Character.isDigit(isbn.charAt(i)))
				return false;
			int digit = isbn.charAt(i) - '0';
			sum += (i % 2 == 0) ? digit : digit * 3;
		}

		return sum % 10 == 0;
	}
}
