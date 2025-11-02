package com.turkcell.book_service.controller;

import com.turkcell.book_service.application.dto.BookResponse;
import com.turkcell.book_service.application.dto.CreateBookRequest;
import com.turkcell.book_service.application.dto.UpdateBookRequest;
import com.turkcell.book_service.application.service.BookApplicationService;
import jakarta.validation.Valid;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST Controller for Book operations
 * Presentation Layer - exposes API endpoints
 */
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookApplicationService bookApplicationService;
    private final StreamBridge streamBridge;

    public BookController(BookApplicationService bookApplicationService, StreamBridge streamBridge) {
        this.bookApplicationService = bookApplicationService;
        this.streamBridge = streamBridge;
    }

    record KafkaTestRequest(String kafkaTest) {
    }

    record KafkaEvent(String kafkaTest) {
    }

    @PostMapping("/kafka-test")
    public String kafkaTest(@RequestBody KafkaTestRequest request) {
        KafkaEvent event = new KafkaEvent(request.kafkaTest);
        streamBridge.send("kafkaTest-out-0", event);
        // Kafka eventi fırlat..
        return request.kafkaTest;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest request) {
        BookResponse response = bookApplicationService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateBookRequest request) {
        BookResponse response = bookApplicationService.updateBook(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable UUID id) {
        BookResponse response = bookApplicationService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponse> getBookByIsbn(@PathVariable String isbn) {
        BookResponse response = bookApplicationService.getBookByIsbn(isbn);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> responses = bookApplicationService.getAllBooks();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> searchBooksByTitle(@RequestParam String title) {
        List<BookResponse> responses = bookApplicationService.searchBooksByTitle(title);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookResponse>> getBooksByAuthor(@PathVariable UUID authorId) {
        List<BookResponse> responses = bookApplicationService.getBooksByAuthor(authorId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/publisher/{publisherId}")
    public ResponseEntity<List<BookResponse>> getBooksByPublisher(@PathVariable UUID publisherId) {
        List<BookResponse> responses = bookApplicationService.getBooksByPublisher(publisherId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/language/{languageId}")
    public ResponseEntity<List<BookResponse>> getBooksByLanguage(@PathVariable UUID languageId) {
        List<BookResponse> responses = bookApplicationService.getBooksByLanguage(languageId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookResponse>> getAvailableBooks() {
        List<BookResponse> responses = bookApplicationService.getAvailableBooks();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookApplicationService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
