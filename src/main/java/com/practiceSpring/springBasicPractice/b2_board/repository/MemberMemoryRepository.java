package com.practiceSpring.springBasicPractice.b2_board.repository;

import com.practiceSpring.springBasicPractice.b2_board.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberMemoryRepository {
    public static Long id = 1L;

    List<Member> members = new ArrayList<>();

    public List<Member> findAll() {
        return members;
    }

    public void save(Member member) {
        members.add(member);
        id++;
    }

    public Optional<Member> findById(Long id) {
        Member member = null;
        for (Member m : members) {
            if (m.getId().equals(id)) {
                member = m;
            }
        }
        return Optional.ofNullable(member);
    }
    public Optional<Member> findByEmail(String email) {
        Member member = null;
        for (Member m : members) {
            if (m.getEmail().equals(email)) {
                member = m;
            }
        }
        return Optional.ofNullable(member);
    }
}
