package com.anaskhaloul.bookwise.catalog;

import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookRepository books;
    public BookController(BookRepository books) { this.books = books; }

    @GetMapping public Page<Book> search(@RequestParam(defaultValue = "") String q,
                                         @RequestParam(defaultValue = "0") @jakarta.validation.constraints.Min(0) int page,
                                         @RequestParam(defaultValue = "20") @jakarta.validation.constraints.Min(1) @jakarta.validation.constraints.Max(100) int size) {
        return books.search(q.trim(), PageRequest.of(page, size, Sort.by("title").ascending()));
    }
    @GetMapping("/{id}") public Book get(@PathVariable long id) {
        return books.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }
    @GetMapping("/summary") public CatalogSummary summary() {
        return new CatalogSummary(books.count(), books.countAvailableCopies());
    }
    @PostMapping public ResponseEntity<Book> create(@Valid @RequestBody BookRequest request) {
        String isbn = request.isbn().replace("-", "").toUpperCase();
        if (books.findByIsbn(isbn).isPresent()) throw new DuplicateIsbnException(isbn);
        Book saved = books.save(new Book(request.title(), request.author(), isbn, request.copies()));
        return ResponseEntity.created(java.net.URI.create("/api/v1/books/" + saved.getId())).body(saved);
    }
}
