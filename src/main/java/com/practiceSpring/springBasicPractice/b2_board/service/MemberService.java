package com.practiceSpring.springBasicPractice.b2_board.service;

import com.practiceSpring.springBasicPractice.b2_board.domain.Member;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberCreateDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberDetailDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberReqList;
import com.practiceSpring.springBasicPractice.b2_board.repository.MemberMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MemberService {

//    DI(의존성 주입) 2번
    private final MemberMemoryRepository memberMemoryRepository;

    @Autowired
    public MemberService(MemberMemoryRepository memberMemoryRepository) {
        this.memberMemoryRepository = memberMemoryRepository;
    }

    public List<MemberReqList> findAll() {
        return memberMemoryRepository.findAll().stream().map(m->m.listFromEntity()).collect(Collectors.toList());
    }

    public void save(MemberCreateDto memberCreateDto)throws IllegalArgumentException {
        if (memberMemoryRepository.findByEmail(memberCreateDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }

        if (memberCreateDto.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호를 8자리 이상으로 설정해주세요.");
        }

        memberMemoryRepository.save(memberCreateDto.memberFromDto());
    }

    public MemberDetailDto findById (Long id) throws NoSuchElementException {
        return memberMemoryRepository.findById(id).orElseThrow(()->new NoSuchElementException("존재하지 않는 ID입니다.")).detailFromEntity();
    }
}
