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

    public MemberService(MemberRepository mbRepository) {
        this.memberRepository = mbRepository;
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

    public List<MemberResponseDTO> getAllMembers() {
        return convertToResponseList(memberRepository.findAll());
    }

    public MemberResponseDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        return toResponseDTO(member);
    }

    public List<MemberResponseDTO> getMemberByName(String name) {
        return convertToResponseList(memberRepository.findByNameContainingIgnoreCase(name));
    }

    public List<MemberResponseDTO> getMemberByAge(Integer age) {
        return convertToResponseList(memberRepository.findByAge(age));
    }

    public List<MemberResponseDTO> getMemberByPosition(String position) {
        return convertToResponseList(memberRepository.findByPosition(position));
    }

    public List<MemberResponseDTO> getMemberByStatus(String status) {
        return convertToResponseList(memberRepository.findByStatus(status));
    }

    public List<MemberResponseDTO> getMemberByCurrentProject(String currentProject) {
        return convertToResponseList(memberRepository.findByCurrentProject(currentProject));
    }

    public MemberResponseDTO getMemberByEmail(String email) {

        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Member not found"));
        return toResponseDTO(member);
    }

    private List<MemberResponseDTO> convertToResponseList(List<Member> members) {

        List<MemberResponseDTO> response = new ArrayList<>();

        for (Member member : members) {
            response.add(toResponseDTO(member));
        }

        return response;
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
