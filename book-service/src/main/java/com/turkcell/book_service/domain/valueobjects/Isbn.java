package com.turkcell.book_service.domain.valueobjects;

import java.util.Objects;

// Value object without persistence annotations
public class Isbn {

    private String value;

    public Isbn() {
    }

    public Isbn(String value) {
        this.value = normalize(value);
        validate(this.value);
    }

    public String getValue() {
        return value;
    }

    private static String normalize(String input) {
        if (input == null) return null;
        return input.replaceAll("[-\\s]", "");
    }

    private static void validate(String isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN cannot be null");
        }
        int len = isbn.length();
        if (len != 10 && len != 13) {
            throw new IllegalArgumentException("ISBN must be 10 or 13 characters");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Isbn isbn1)) return false;
        return Objects.equals(value, isbn1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}


