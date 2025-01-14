package com.practiceSpring.springBasicPractice.b2_board.repository;

import com.practiceSpring.springBasicPractice.b2_board.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberMemoryRepository {
    List<Member> membersList = new ArrayList<>();
    public static Long id = 1L;

    public List<Member> findAll() {
        return membersList;
    }

    public Optional<Member> findById(Long id) {
        Member member =  null;
        for (Member m : membersList) {
            if (m.getId().equals(id)) {
                member = m;
            }
        }
        return Optional.ofNullable(member);
    }

    public void save(Member member) {
        membersList.add(member);
        id++;
    }

    public Optional<Member> findByEmail(String email) {
        Member member = null;
        for (Member m : membersList) {
            if (m.getEmail().equals(email)) {
                member = m;
            }
        }

        return Optional.ofNullable(member);
    }
}
