package org.example.library.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private LocalDate publishedDate;

    public Book() {}

    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus = AvailabilityStatus.AVAILABLE;

    @ManyToOne
    private Member borrowedBy;

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }
    public String getAuthor() { return author; }
    public void setAuthor(String a) { this.author = a; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String i) { this.isbn = i; }
    public LocalDate getPublishedDate() { return publishedDate; }
    public void setPublishedDate(LocalDate d) { this.publishedDate = d; }
    public AvailabilityStatus getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(AvailabilityStatus s) { this.availabilityStatus = s; }
    public Member getBorrowedBy() { return borrowedBy; }
    public void setBorrowedBy(Member m) { this.borrowedBy = m; }
}
