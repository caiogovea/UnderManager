package com.underveil.manager.controller;

import com.underveil.manager.dto.CreateMemberDTO;
import com.underveil.manager.dto.MemberResponseDTO;
import com.underveil.manager.dto.UpdateMemberDTO;
import com.underveil.manager.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping
    public List<MemberResponseDTO> findAll() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @GetMapping(value = "/search", params = "name")
    public ResponseEntity<List<MemberResponseDTO>> getMemberByName(@RequestParam String name) {
        return ResponseEntity.ok(memberService.getMemberByName(name));
    }

    @GetMapping(value = "/search", params = "age")
    public ResponseEntity<List<MemberResponseDTO>> getMemberByAge(@RequestParam Integer age) {
        return ResponseEntity.ok(memberService.getMemberByAge(age));
    }

    @GetMapping(value = "/search", params = "email")
    public ResponseEntity<MemberResponseDTO> getMemberByEmail(
            @RequestParam String email) {

        return ResponseEntity.ok(
                memberService.getMemberByEmail(email)
        );
    }

    @GetMapping(value = "/search", params = "position")
    public ResponseEntity<List<MemberResponseDTO>> getMemberByPosition(@RequestParam String position) {
        return ResponseEntity.ok(memberService.getMemberByPosition(position));
    }

    @GetMapping(value = "/search", params = "status")
    public ResponseEntity<List<MemberResponseDTO>> getMemberByStatus(@RequestParam String status) {
        return ResponseEntity.ok(memberService.getMemberByStatus(status));
    }

    @GetMapping(value = "/search", params = "currentProject")
    public ResponseEntity<List<MemberResponseDTO>> getMemberByCurrentProject(@RequestParam String currentProject) {
        return ResponseEntity.ok(memberService.getMemberByCurrentProject(currentProject));
    }


    @PostMapping
    public ResponseEntity<MemberResponseDTO> createMember(@Valid @RequestBody CreateMemberDTO member) {

        MemberResponseDTO savedMember = memberService.createMember(member);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> updateMember(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMemberDTO member) {

        MemberResponseDTO savedMember = memberService.updateMember(id, member);

        return ResponseEntity.ok(savedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember (@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}

