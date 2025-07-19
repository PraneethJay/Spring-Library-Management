package org.example.library.service;

import org.example.library.entity.Member;
import org.example.library.exception.NotFoundException;
import org.example.library.exception.BadRequestException;
import org.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    @Autowired
    private MemberRepository memberRepo;

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Member not found"));

        if (member.getBorrowedBooks() != null && !member.getBorrowedBooks().isEmpty()) {
            throw new BadRequestException("Cannot delete member. Member has borrowed books.");
        }

        memberRepo.delete(member);
    }
}

