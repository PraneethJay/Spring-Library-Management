package org.example.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    public Member() {
    }

    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @OneToMany(mappedBy = "borrowedBy")
    @JsonIgnore  // ← ADD THIS LINE to avoid lazy loading error during JSON serialization
    private List<Book> borrowedBooks;

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String n) { this.name = n; }
    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
    public void setBorrowedBooks(List<Book> b) { this.borrowedBooks = b; }
}
