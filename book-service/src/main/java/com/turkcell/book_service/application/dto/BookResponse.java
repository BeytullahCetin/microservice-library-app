package com.turkcell.book_service.application.dto;

import com.turkcell.book_service.domain.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * DTO for book responses
 */
public class BookResponse {
    private UUID id;
    private String isbn;
    private String title;
    private String description;
    private int pageCount;
    private LocalDate publicationDate;
    private PublisherDto publisher;
    private LanguageDto language;
    private List<AuthorDto> authors;
    private List<TranslatorDto> translators;
    private boolean available;
    private int availableCopies;
    private int totalCopies;
    
    // Constructors
    public BookResponse() {
    }
    
    // Factory method to create from domain entity
    public static BookResponse fromDomain(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setIsbn(book.getIsbn());
        response.setTitle(book.getTitle());
        response.setDescription(book.getDescription());
        response.setPageCount(book.getPageCount());
        response.setPublicationDate(book.getPublicationDate());
        response.setAvailable(book.isAvailable());
        response.setAvailableCopies(book.getAvailableCopiesCount());
        response.setTotalCopies(book.getTotalCopiesCount());
        
        if (book.getPublisher() != null) {
            response.setPublisher(PublisherDto.fromDomain(book.getPublisher()));
        }
        
        if (book.getLanguage() != null) {
            response.setLanguage(LanguageDto.fromDomain(book.getLanguage()));
        }
        
        response.setAuthors(book.getAuthors().stream()
                .map(AuthorDto::fromDomain)
                .collect(Collectors.toList()));
        
        response.setTranslators(book.getTranslators().stream()
                .map(TranslatorDto::fromDomain)
                .collect(Collectors.toList()));
        
        return response;
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getPageCount() {
        return pageCount;
    }
    
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
    
    public PublisherDto getPublisher() {
        return publisher;
    }
    
    public void setPublisher(PublisherDto publisher) {
        this.publisher = publisher;
    }
    
    public LanguageDto getLanguage() {
        return language;
    }
    
    public void setLanguage(LanguageDto language) {
        this.language = language;
    }
    
    public List<AuthorDto> getAuthors() {
        return authors;
    }
    
    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }
    
    public List<TranslatorDto> getTranslators() {
        return translators;
    }
    
    public void setTranslators(List<TranslatorDto> translators) {
        this.translators = translators;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public int getAvailableCopies() {
        return availableCopies;
    }
    
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    
    public int getTotalCopies() {
        return totalCopies;
    }
    
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
    
    // Nested DTOs
    public static class AuthorDto {
        private UUID id;
        private String firstName;
        private String lastName;
        private String fullName;
        private String nationality;
        
        public static AuthorDto fromDomain(Author author) {
            AuthorDto dto = new AuthorDto();
            dto.setId(author.getId());
            dto.setFirstName(author.getFirstName());
            dto.setLastName(author.getLastName());
            dto.setFullName(author.getFullName());
            dto.setNationality(author.getNationality());
            return dto;
        }
        
        // Getters and Setters
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
        public String getNationality() { return nationality; }
        public void setNationality(String nationality) { this.nationality = nationality; }
    }
    
    public static class PublisherDto {
        private UUID id;
        private String name;
        private String country;
        
        public static PublisherDto fromDomain(Publisher publisher) {
            PublisherDto dto = new PublisherDto();
            dto.setId(publisher.getId());
            dto.setName(publisher.getName());
            dto.setCountry(publisher.getCountry());
            return dto;
        }
        
        // Getters and Setters
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }
    
    public static class LanguageDto {
        private UUID id;
        private String code;
        private String name;
        
        public static LanguageDto fromDomain(Language language) {
            LanguageDto dto = new LanguageDto();
            dto.setId(language.getId());
            dto.setCode(language.getCode());
            dto.setName(language.getName());
            return dto;
        }
        
        // Getters and Setters
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
    
    public static class TranslatorDto {
        private UUID id;
        private String firstName;
        private String lastName;
        private String fullName;
        
        public static TranslatorDto fromDomain(Translator translator) {
            TranslatorDto dto = new TranslatorDto();
            dto.setId(translator.getId());
            dto.setFirstName(translator.getFirstName());
            dto.setLastName(translator.getLastName());
            dto.setFullName(translator.getFullName());
            return dto;
        }
        
        // Getters and Setters
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
    }
}

