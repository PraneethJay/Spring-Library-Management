package org.example.library.service;

import org.example.library.entity.Book;
import org.example.library.entity.Member;
import org.example.library.entity.AvailabilityStatus;
import org.example.library.exception.NotFoundException;
import org.example.library.exception.BadRequestException;
import org.example.library.repository.BookRepository;
import org.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private MemberRepository memberRepo;

    public Book borrowBook(Long bookId, Long memberId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        if (book.getAvailabilityStatus() != AvailabilityStatus.AVAILABLE) {
            throw new BadRequestException("Book is not available");
        }

        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new NotFoundException("Member not found"));

        book.setBorrowedBy(member);
        book.setAvailabilityStatus(AvailabilityStatus.BORROWED);
        return bookRepo.save(book);
    }

    public Book returnBook(Long bookId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        book.setBorrowedBy(null);
        book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        return bookRepo.save(book);
    }
}
