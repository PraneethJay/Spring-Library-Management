//package org.example.library.controller;
//
//import org.example.library.entity.Member;
//import org.example.library.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class MemberControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
//    public void testGetAllMembers() throws Exception {
//        memberRepository.save(new Member(null, "John", "john@example.com"));
//        memberRepository.save(new Member(null, "Jane", "jane@example.com"));
//
//        mockMvc.perform(get("/members"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(2)));
//    }
//}
