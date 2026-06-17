package com.underveil.manager.service;

import com.underveil.manager.dto.CreateMemberDTO;
import com.underveil.manager.dto.MemberResponseDTO;
import com.underveil.manager.dto.UpdateMemberDTO;
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

    public Member createMember(CreateMemberDTO memberDTO) {

        Member member = new Member();

        member.setName(memberDTO.getName());
        member.setAge(memberDTO.getAge());
        member.setEmail(memberDTO.getEmail());
        member.setPosition(memberDTO.getPosition());
        member.setStatus(memberDTO.getStatus());
        member.setCurrentProject(memberDTO.getCurrentProject());

        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Member not found"));
    }

    public Optional<Member> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member updateMember(Long id, UpdateMemberDTO memberDTO) {

        Member existingMember = getMemberById(id);

        existingMember.setName(memberDTO.getName());
        existingMember.setAge(memberDTO.getAge());
        existingMember.setEmail(memberDTO.getEmail());
        existingMember.setPosition(memberDTO.getPosition());
        existingMember.setStatus(memberDTO.getStatus());
        existingMember.setCurrentProject(memberDTO.getCurrentProject());

        return memberRepository.save(existingMember);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    private MemberResponseDTO toResponseDTO(Member member) {

        return new MemberResponseDTO(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getEmail(),
                member.getPosition(),
                member.getStatus(),
                member.getCurrentProject()
        );
    }
}
