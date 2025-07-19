//package org.example.library.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.library.entity.Book;
//import org.example.library.repository.BookRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureJsonTesters // ✅ Fixes ObjectMapper injection
//public class BookControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper; // ✅ Will now be injected
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @BeforeEach
//    void setup() {
//        bookRepository.deleteAll();
//    }
//
//    @Test
//    void testGetAllBooks() throws Exception {
//        Book book1 = new Book(null, "Book 1", "Author A");
//        Book book2 = new Book(null, "Book 2", "Author B");
//        bookRepository.saveAll(List.of(book1, book2));
//
//        mockMvc.perform(get("/books"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)));
//    }
//
//    @Test
//    void testCreateBook() throws Exception {
//        Book newBook = new Book(null, "Test Book", "Test Author");
//
//        mockMvc.perform(post("/books")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newBook)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.title").value("Test Book"));
//    }
//}
