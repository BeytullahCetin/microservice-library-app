package com.turkcell.book_service.domain.valueobjects;

import java.util.Objects;

/**
 * ISBN Value Object
 * Immutable value object for ISBN validation and formatting
 */
public class Isbn {
    private final String value;
    
    public Isbn(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        
        String cleanValue = value.replaceAll("[^0-9X]", "");
        
        if (!isValidIsbn10(cleanValue) && !isValidIsbn13(cleanValue)) {
            throw new IllegalArgumentException("Invalid ISBN format: " + value);
        }
        
        this.value = cleanValue;
    }
    
    private boolean isValidIsbn10(String isbn) {
        if (isbn.length() != 10) {
            return false;
        }
        
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            char c = isbn.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
            sum += (c - '0') * (10 - i);
        }
        
        char lastChar = isbn.charAt(9);
        if (lastChar == 'X') {
            sum += 10;
        } else if (Character.isDigit(lastChar)) {
            sum += (lastChar - '0');
        } else {
            return false;
        }
        
        return sum % 11 == 0;
    }
    
    private boolean isValidIsbn13(String isbn) {
        if (isbn.length() != 13) {
            return false;
        }
        
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            char c = isbn.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
            int digit = c - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        
        char lastChar = isbn.charAt(12);
        if (!Character.isDigit(lastChar)) {
            return false;
        }
        
        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit == (lastChar - '0');
    }
    
    public String getValue() {
        return value;
    }
    
    public String getFormatted() {
        if (value.length() == 10) {
            return String.format("%s-%s-%s-%s-%s",
                    value.substring(0, 1),
                    value.substring(1, 5),
                    value.substring(5, 9),
                    value.substring(9, 10));
        } else if (value.length() == 13) {
            return String.format("%s-%s-%s-%s-%s",
                    value.substring(0, 3),
                    value.substring(3, 4),
                    value.substring(4, 9),
                    value.substring(9, 12),
                    value.substring(12, 13));
        }
        return value;
    }
    
    public boolean isIsbn10() {
        return value.length() == 10;
    }
    
    public boolean isIsbn13() {
        return value.length() == 13;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Isbn)) return false;
        Isbn isbn = (Isbn) o;
        return Objects.equals(value, isbn.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return getFormatted();
    }
}

