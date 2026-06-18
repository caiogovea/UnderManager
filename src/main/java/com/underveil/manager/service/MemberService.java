package com.underveil.manager.service;

import com.underveil.manager.dto.CreateMemberDTO;
import com.underveil.manager.dto.MemberResponseDTO;
import com.underveil.manager.dto.UpdateMemberDTO;
import com.underveil.manager.entity.Member;
import com.underveil.manager.exception.ResourceNotFoundException;
import com.underveil.manager.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository mbrepository) {
        this.memberRepository = mbrepository;
    }

    public List<MemberResponseDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDTO> response = new ArrayList<>();
        for (Member member : members) {
            response.add(toResponseDTO(member));
        }
        return response;
    }

    public MemberResponseDTO createMember(CreateMemberDTO memberDTO) {

        Member member = new Member();

        member.setName(memberDTO.getName());
        member.setAge(memberDTO.getAge());
        member.setEmail(memberDTO.getEmail());
        member.setPosition(memberDTO.getPosition());
        member.setStatus(memberDTO.getStatus());
        member.setCurrentProject(memberDTO.getCurrentProject());

        Member savedMember = memberRepository.save(member);
        return toResponseDTO(savedMember);

    }

    public MemberResponseDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Member not found"));

        return toResponseDTO(member);
    }

    public Optional<Member> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public MemberResponseDTO updateMember(Long id, UpdateMemberDTO memberDTO) {

        Member existingMember = memberRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Member not found"));

        existingMember.setName(memberDTO.getName());
        existingMember.setAge(memberDTO.getAge());
        existingMember.setEmail(memberDTO.getEmail());
        existingMember.setPosition(memberDTO.getPosition());
        existingMember.setStatus(memberDTO.getStatus());
        existingMember.setCurrentProject(memberDTO.getCurrentProject());

        Member savedMember = memberRepository.save(existingMember);
        return toResponseDTO(savedMember);
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
