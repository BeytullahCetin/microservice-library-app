package com.turkcell.book_service.application.service;

import com.turkcell.book_service.application.dto.*;
import com.turkcell.book_service.domain.entities.*;
import com.turkcell.book_service.domain.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Application Service for Book operations
 * Orchestrates domain operations and coordinates between domain and infrastructure
 */
@Service
@Transactional
public class BookApplicationService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final LanguageRepository languageRepository;
    private final TranslatorRepository translatorRepository;
    private final BookCopyRepository bookCopyRepository;
    
    public BookApplicationService(BookRepository bookRepository,
                                  AuthorRepository authorRepository,
                                  PublisherRepository publisherRepository,
                                  LanguageRepository languageRepository,
                                  TranslatorRepository translatorRepository,
                                  BookCopyRepository bookCopyRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.languageRepository = languageRepository;
        this.translatorRepository = translatorRepository;
        this.bookCopyRepository = bookCopyRepository;
    }
    
    public BookResponse createBook(CreateBookRequest request) {
        // Validate ISBN uniqueness
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + request.getIsbn() + " already exists");
        }
        
        // Fetch related entities
        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
        
        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new IllegalArgumentException("Language not found"));
        
        List<Author> authors = request.getAuthorIds().stream()
                .map(id -> authorRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Author not found: " + id)))
                .collect(Collectors.toList());
        
        // Create book entity
        Book book = new Book(
                request.getIsbn(),
                request.getTitle(),
                request.getDescription(),
                request.getPageCount(),
                request.getPublicationDate(),
                publisher,
                language
        );
        
        // Add authors
        authors.forEach(book::addAuthor);
        
        // Add translators if provided
        if (request.getTranslatorIds() != null && !request.getTranslatorIds().isEmpty()) {
            List<Translator> translators = request.getTranslatorIds().stream()
                    .map(id -> translatorRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Translator not found: " + id)))
                    .collect(Collectors.toList());
            translators.forEach(book::addTranslator);
        }
        
        // Validate and save
        book.validateIsbn();
        Book savedBook = bookRepository.save(book);
        
        return BookResponse.fromDomain(savedBook);
    }
    
    public BookResponse updateBook(UUID id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        
        // Fetch related entities
        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
        
        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new IllegalArgumentException("Language not found"));
        
        List<Author> authors = request.getAuthorIds().stream()
                .map(authorId -> authorRepository.findById(authorId)
                        .orElseThrow(() -> new IllegalArgumentException("Author not found: " + authorId)))
                .collect(Collectors.toList());
        
        // Update book properties
        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setPageCount(request.getPageCount());
        book.setPublicationDate(request.getPublicationDate());
        book.setPublisher(publisher);
        book.setLanguage(language);
        book.setAuthors(authors);
        
        // Update translators if provided
        if (request.getTranslatorIds() != null) {
            List<Translator> translators = request.getTranslatorIds().stream()
                    .map(translatorId -> translatorRepository.findById(translatorId)
                            .orElseThrow(() -> new IllegalArgumentException("Translator not found: " + translatorId)))
                    .collect(Collectors.toList());
            book.setTranslators(translators);
        }
        
        Book updatedBook = bookRepository.save(book);
        return BookResponse.fromDomain(updatedBook);
    }
    
    @Transactional(readOnly = true)
    public BookResponse getBookById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return BookResponse.fromDomain(book);
    }
    
    @Transactional(readOnly = true)
    public BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return BookResponse.fromDomain(book);
    }
    
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookResponse::fromDomain)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<BookResponse> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContaining(title).stream()
                .map(BookResponse::fromDomain)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByAuthor(UUID authorId) {
        return bookRepository.findByAuthorId(authorId).stream()
                .map(BookResponse::fromDomain)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByPublisher(UUID publisherId) {
        return bookRepository.findByPublisherId(publisherId).stream()
                .map(BookResponse::fromDomain)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByLanguage(UUID languageId) {
        return bookRepository.findByLanguageId(languageId).stream()
                .map(BookResponse::fromDomain)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<BookResponse> getAvailableBooks() {
        return bookRepository.findAvailableBooks().stream()
                .map(BookResponse::fromDomain)
                .collect(Collectors.toList());
    }
    
    public void deleteBook(UUID id) {
        if (!bookRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Book not found");
        }
        bookRepository.deleteById(id);
    }
    
    // Book Copy operations
    public BookCopyResponse createBookCopy(CreateBookCopyRequest request) {
        if (bookCopyRepository.existsByBarcode(request.getBarcode())) {
            throw new IllegalArgumentException("Book copy with barcode " + request.getBarcode() + " already exists");
        }
        
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        
        BookCopy bookCopy = new BookCopy(request.getBarcode(), book, request.getLocation());
        bookCopy.updateCondition(request.getCondition());
        bookCopy.validate();
        
        BookCopy savedCopy = bookCopyRepository.save(bookCopy);
        
        return BookCopyResponse.fromDomain(savedCopy);
    }
    
    @Transactional(readOnly = true)
    public List<BookCopyResponse> getBookCopiesByBookId(UUID bookId) {
        return bookCopyRepository.findByBookId(bookId).stream()
                .map(BookCopyResponse::fromDomain)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public BookCopyResponse getBookCopyByBarcode(String barcode) {
        BookCopy bookCopy = bookCopyRepository.findByBarcode(barcode)
                .orElseThrow(() -> new IllegalArgumentException("Book copy not found"));
        return BookCopyResponse.fromDomain(bookCopy);
    }
    
    public BookCopyResponse borrowBookCopy(String barcode) {
        BookCopy bookCopy = bookCopyRepository.findByBarcode(barcode)
                .orElseThrow(() -> new IllegalArgumentException("Book copy not found"));
        
        bookCopy.borrow();
        BookCopy savedCopy = bookCopyRepository.save(bookCopy);
        
        return BookCopyResponse.fromDomain(savedCopy);
    }
    
    public BookCopyResponse returnBookCopy(String barcode) {
        BookCopy bookCopy = bookCopyRepository.findByBarcode(barcode)
                .orElseThrow(() -> new IllegalArgumentException("Book copy not found"));
        
        bookCopy.returnCopy();
        BookCopy savedCopy = bookCopyRepository.save(bookCopy);
        
        return BookCopyResponse.fromDomain(savedCopy);
    }
}

