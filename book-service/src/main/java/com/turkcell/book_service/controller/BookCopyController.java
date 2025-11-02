package com.turkcell.book_service.controller;

import com.turkcell.book_service.application.dto.BookCopyResponse;
import com.turkcell.book_service.application.dto.CreateBookCopyRequest;
import com.turkcell.book_service.application.service.BookApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST Controller for BookCopy operations
 */
@RestController
@RequestMapping("/api/v1/book-copies")
public class BookCopyController {
    
    private final BookApplicationService bookApplicationService;
    
    public BookCopyController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }
    
    @PostMapping
    public ResponseEntity<BookCopyResponse> createBookCopy(@Valid @RequestBody CreateBookCopyRequest request) {
        BookCopyResponse response = bookApplicationService.createBookCopy(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BookCopyResponse>> getBookCopiesByBookId(@PathVariable UUID bookId) {
        List<BookCopyResponse> responses = bookApplicationService.getBookCopiesByBookId(bookId);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<BookCopyResponse> getBookCopyByBarcode(@PathVariable String barcode) {
        BookCopyResponse response = bookApplicationService.getBookCopyByBarcode(barcode);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{barcode}/borrow")
    public ResponseEntity<BookCopyResponse> borrowBookCopy(@PathVariable String barcode) {
        BookCopyResponse response = bookApplicationService.borrowBookCopy(barcode);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{barcode}/return")
    public ResponseEntity<BookCopyResponse> returnBookCopy(@PathVariable String barcode) {
        BookCopyResponse response = bookApplicationService.returnBookCopy(barcode);
        return ResponseEntity.ok(response);
    }
}

