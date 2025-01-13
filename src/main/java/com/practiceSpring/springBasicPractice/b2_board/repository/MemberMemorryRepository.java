package com.practiceSpring.springBasicPractice.b2_board.repository;

import com.practiceSpring.springBasicPractice.b2_board.domain.Member;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberListRes;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberMemorryRepository {
    public static Long id = 1L;
    List<Member> memberList = new ArrayList<>();

    public List<Member> findAll() {
        return this.memberList;
    }

    public void save(Member member) {
        memberList.add(member);
        id++;
    }

}
