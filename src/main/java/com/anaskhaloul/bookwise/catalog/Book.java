package com.anaskhaloul.bookwise.catalog;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "books", indexes = @Index(name = "idx_books_isbn", columnList = "isbn", unique = true))
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200) private String title;
    @Column(nullable = false, length = 160) private String author;
    @Column(nullable = false, length = 20) private String isbn;
    @Column(nullable = false) private int totalCopies;
    @Column(nullable = false) private int availableCopies;
    @Column(nullable = false, updatable = false) private Instant createdAt;
    @Version private long version;

    protected Book() { }
    public Book(String title, String author, String isbn, int copies) {
        this.title = title.trim(); this.author = author.trim(); this.isbn = isbn.trim();
        this.totalCopies = copies; this.availableCopies = copies; this.createdAt = Instant.now();
    }
    public void checkout() {
        if (availableCopies < 1) throw new IllegalStateException("No copies are currently available");
        availableCopies--;
    }
    public void returnCopy() {
        if (availableCopies >= totalCopies) throw new IllegalStateException("All copies are already in the library");
        availableCopies++;
    }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }
    public Instant getCreatedAt() { return createdAt; }
}
