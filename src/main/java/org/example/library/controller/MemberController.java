package org.example.library.controller;

import org.example.library.exception.BadRequestException;
import org.example.library.exception.NotFoundException;
import org.example.library.service.MemberService;
import org.example.library.entity.Member;
import org.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private MemberService memberService;

    @GetMapping
    public ResponseEntity<List<Member>> list() {
        return ResponseEntity.ok(memberRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member member) {
        Member saved = memberRepo.save(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Member updatedMember) {
        Optional<Member> optionalMember = memberRepo.findById(id);
        if (optionalMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Member not found"));
        }
        Member member = optionalMember.get();
        member.setName(updatedMember.getName());
        member.setEmail(updatedMember.getEmail());
        memberRepo.save(member);
        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        try {
            memberService.deleteMember(id);
            return ResponseEntity.ok(Map.of("message", "Member successfully deleted."));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
