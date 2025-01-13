package com.practiceSpring.springBasicPractice.b2_board.repository;

import com.practiceSpring.springBasicPractice.b2_board.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
}
