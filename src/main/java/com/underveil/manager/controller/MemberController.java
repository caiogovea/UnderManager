package com.underveil.manager.controller;

import com.underveil.manager.dto.CreateMemberDTO;
import com.underveil.manager.dto.UpdateMemberDTO;
import com.underveil.manager.entity.Member;
import com.underveil.manager.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
    public class MemberController {

    private final MemberService memberService;


    @GetMapping
    public List<Member> findAll() {
          return memberService.getAllMembers();
      }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@Valid @RequestBody CreateMemberDTO member) {

        Member savedMember = memberService.createMember(member);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMemberDTO member) {

        Member savedMember = memberService.updateMember(id, member);

        return ResponseEntity.ok(savedMember);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember (@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }




}

