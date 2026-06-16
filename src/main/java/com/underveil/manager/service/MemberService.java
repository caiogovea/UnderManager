package com.underveil.manager.service;

import com.underveil.manager.entity.Member;
import com.underveil.manager.exception.ResourceNotFoundException;
import com.underveil.manager.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository mbrepository) {
        this.memberRepository = mbrepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Member not found"));
    }

    public Optional<Member> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member updateMember(Long id, Member member) {

        Member existingMember = getMemberById(id);

        existingMember.setName(member.getName());
        existingMember.setAge(member.getAge());
        existingMember.setEmail(member.getEmail());
        existingMember.setPosition(member.getPosition());
        existingMember.setStatus(member.getStatus());
        existingMember.setCurrentProject(member.getCurrentProject());

        return memberRepository.save(existingMember);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }



}
