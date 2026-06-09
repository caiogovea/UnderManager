package com.underveil.manager.controller;

import com.underveil.manager.entity.Member;
import com.underveil.manager.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
    public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
            this.memberService = memberService;
        }

    @GetMapping
    public List<Member> findAll() {
          return memberService.getAllMembers();
      }


}

