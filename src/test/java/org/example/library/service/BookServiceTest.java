//package org.example.library.service;
//
//
//import org.example.library.entity.*;
//import org.example.library.exception.*;
//import org.example.library.repository.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceTest {
//
//    @Mock
//    private BookRepository bookRepo;
//
//    @Mock
//    private MemberRepository memberRepo;
//
//    @InjectMocks
//    private BookService bookService;
//
//    @Test
//    void borrowBook_success() {
//        Long bookId = 1L;
//        Long memberId = 1L;
//
//        Book book = new Book();
//        book.setId(bookId);
//        book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
//
//        Member member = new Member();
//        member.setId(memberId);
//
//        when(bookRepo.findById(bookId)).thenReturn(Optional.of(book));
//        when(memberRepo.findById(memberId)).thenReturn(Optional.of(member));
//        when(bookRepo.save(any(Book.class))).thenAnswer(i -> i.getArgument(0));
//
//        Book borrowedBook = bookService.borrowBook(bookId, memberId);
//
//        assertEquals(AvailabilityStatus.BORROWED, borrowedBook.getAvailabilityStatus());
//        assertEquals(member, borrowedBook.getBorrowedBy());
//        verify(bookRepo).save(borrowedBook);
//    }
//
//    @Test
//    void borrowBook_notAvailable_throws() {
//        Book book = new Book();
//        book.setId(1L);
//        book.setAvailabilityStatus(AvailabilityStatus.BORROWED);
//
//        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
//
//        BadRequestException ex = assertThrows(BadRequestException.class,
//                () -> bookService.borrowBook(1L, 1L));
//        assertEquals("Book is not available", ex.getMessage());
//    }
//
//    @Test
//    void borrowBook_bookNotFound_throws() {
//        when(bookRepo.findById(1L)).thenReturn(Optional.empty());
//        NotFoundException ex = assertThrows(NotFoundException.class,
//                () -> bookService.borrowBook(1L, 1L));
//        assertEquals("Book not found", ex.getMessage());
//    }
//
//    @Test
//    void returnBook_success() {
//        Book book = new Book();
//        book.setId(1L);
//        book.setAvailabilityStatus(AvailabilityStatus.BORROWED);
//        book.setBorrowedBy(new Member());
//
//        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
//        when(bookRepo.save(any(Book.class))).thenAnswer(i -> i.getArgument(0));
//
//        Book returnedBook = bookService.returnBook(1L);
//
//        assertEquals(AvailabilityStatus.AVAILABLE, returnedBook.getAvailabilityStatus());
//        assertNull(returnedBook.getBorrowedBy());
//    }
//}
//
