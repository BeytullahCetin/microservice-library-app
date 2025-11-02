package com.turkcell.book_service.domain.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Embeddable
public class Isbn {

    @NotBlank
    @Column(name = "isbn", nullable = false, unique = true, length = 17)
    private String value;

    protected Isbn() {
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
        // Simple length check (10 or 13). Implement full checksum if needed.
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


