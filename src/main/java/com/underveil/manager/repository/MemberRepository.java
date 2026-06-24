package com.underveil.manager.repository;

import com.underveil.manager.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByAge(Integer age);
    Optional<Member> findByEmail(String email);
    List<Member> findByPosition(String position);
    List<Member> findByStatus(String status);
    List<Member> findByCurrentProject(String currentProject);

}
