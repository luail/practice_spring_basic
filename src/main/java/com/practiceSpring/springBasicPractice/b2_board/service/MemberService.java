package com.practiceSpring.springBasicPractice.b2_board.service;

import com.practiceSpring.springBasicPractice.b2_board.domain.Member;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberCreateDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberDetailDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberListRes;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberReqDto;
import com.practiceSpring.springBasicPractice.b2_board.repository.MemberMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberMemoryRepository memberMemoryRepository;

    public List<MemberListRes> findAll() {
//        List<Member> members = memberMemoryRepository.findAll();
//        List<MemberListRes> memberListResList = new ArrayList<>();
//        for (Member m : members) {
//            MemberListRes m1 = m.listFromEntity();
//            memberListResList.add(m1);
//        }
//        return memberListResList;
        return memberMemoryRepository.findAll().stream().map(m->m.listFromEntity()).collect(Collectors.toList());

    }

    public void save(MemberCreateDto memberCreateDto)throws IllegalArgumentException {
        if (memberMemoryRepository.findByEmail(memberCreateDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }

        if (memberCreateDto.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호를 8자리 이상으로 설정해주세요");
        }

        memberMemoryRepository.save(memberCreateDto.toEntity());
    }

    public MemberDetailDto findById (Long id) throws NoSuchElementException {
        return memberMemoryRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("없는 ID입니다."))
                .detailFromEntity();
    }

}
