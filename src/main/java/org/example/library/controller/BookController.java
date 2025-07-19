package org.example.library.controller;

import org.example.library.entity.AvailabilityStatus;
import org.example.library.entity.Book;
import org.example.library.repository.BookRepository;
import org.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BookService bookService;

    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> listBooks() {
        List<Book> books = bookRepo.findAll();
        return ResponseEntity.ok(books);
    }

    // Create a new book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book saved = bookRepo.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Update existing book
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Book not found"));
        }
        Book book = optionalBook.get();
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        book.setPublishedDate(updatedBook.getPublishedDate());
        book.setAvailabilityStatus(updatedBook.getAvailabilityStatus());
        bookRepo.save(book);
        return ResponseEntity.ok(book);
    }

    // Borrow a book
    @PostMapping("/{bookId}/borrow/{memberId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @PathVariable Long memberId) {
        try {
            Book book = bookService.borrowBook(bookId, memberId);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Return a book
    @PostMapping("/{bookId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId) {
        try {
            Book book = bookService.returnBook(bookId);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Delete a book (only if available)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Book not found"));
        }
        Book book = optionalBook.get();
        if (book.getAvailabilityStatus() != AvailabilityStatus.AVAILABLE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Cannot delete book. It is currently borrowed or reserved."));
        }
        bookRepo.delete(book);
        return ResponseEntity.ok(Map.of("message", "Book successfully deleted."));
    }
}

