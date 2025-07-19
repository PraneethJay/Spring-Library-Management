//package org.example.library.service;
//
//import org.example.library.entity.Member;
//import org.example.library.repository.MemberRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class MemberServiceTest {
//
//    private MemberRepository memberRepository;
//    private MemberService memberService;
//
//    @BeforeEach
//    void setUp() {
//        memberRepository = mock(MemberRepository.class);
//        memberService = new MemberService(memberRepository);
//    }
//
//    @Test
//    void testGetMemberById() {
//        Member member = new Member(1L, "Alice", "alice@example.com");
//        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
//
//        Member result = memberService.getMemberById(1L);
//
//        assertNotNull(result);
//        assertEquals("Alice", result.getName());
//    }
//}
