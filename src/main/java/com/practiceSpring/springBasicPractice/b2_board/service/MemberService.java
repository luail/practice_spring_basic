package com.practiceSpring.springBasicPractice.b2_board.service;

import com.practiceSpring.springBasicPractice.b2_board.domain.Member;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberListRes;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberReqDto;
import com.practiceSpring.springBasicPractice.b2_board.repository.MemberMemorryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberMemorryRepository memberMemorryRepository;

    public List<MemberListRes> findAll() {
        List<Member> members = memberMemorryRepository.findAll();
        List<MemberListRes> memberListResList = new ArrayList<>();
        for (Member m : members) {
            MemberListRes ml = new MemberListRes(m.getId(), m.getName(), m.getEmail());
            memberListResList.add(ml);
        }
        return memberListResList;
    }

    public void save(MemberReqDto memberReqDto) {
        Member member = new Member(memberMemorryRepository.id, memberReqDto.getName(), memberReqDto.getEmail(), memberReqDto.getPassword());
        memberMemorryRepository.save(member);
    }
}
