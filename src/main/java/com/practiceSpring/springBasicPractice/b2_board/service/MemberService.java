package com.practiceSpring.springBasicPractice.b2_board.service;

import com.practiceSpring.springBasicPractice.b2_board.domain.Member;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberListRes;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberReqDto;
import com.practiceSpring.springBasicPractice.b2_board.repository.MemberMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberMemoryRepository memberMemoryRepository;

    public List<MemberListRes> findAll() {
        List<Member> members = memberMemoryRepository.findAll();
        List<MemberListRes> memberListResList = new ArrayList<>();
        for (Member m : members) {
            MemberListRes m1 = new MemberListRes(m.getId(), m.getName(), m.getEmail());
            memberListResList.add(m1);
        }
        return memberListResList;
    }

    public void save(MemberReqDto memberReqDto) {
        Member member1 = new Member(memberMemoryRepository.id, memberReqDto.getName(), memberReqDto.getEmail(), memberReqDto.getPassword());
        memberMemoryRepository.save(member1);
    }

}
