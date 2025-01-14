package com.practiceSpring.springBasicPractice.b2_board.service;

import com.practiceSpring.springBasicPractice.b2_board.domain.Member;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberCreateDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberDetailDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberResList;
import com.practiceSpring.springBasicPractice.b2_board.repository.MemberMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MemberService {
//    DI(의존성 주입) 방법2
//    의존성을 주입할 final로 Repository 선언.
//    그 이후 생성자에서 주입 또한 위에 Autiwired어노테이션 사용.
    private final MemberMemoryRepository memberMemoryRepository;
    @Autowired
    public MemberService(MemberMemoryRepository memberMemoryRepository) {
        this.memberMemoryRepository = memberMemoryRepository;
    }

    public List<MemberResList> findAll() {
       return memberMemoryRepository.findAll().stream().map(m->m.resListFromEntity()).collect(Collectors.toList());
    }

    public MemberDetailDto findById(Long id) throws NoSuchElementException {
        return memberMemoryRepository.findById(id).orElseThrow(()->new NoSuchElementException("존재하지 않는 ID입니다.")).detailFromEntity();
    }

    public void save(MemberCreateDto memberCreateDto) throws IllegalArgumentException {
        if (memberMemoryRepository.findByEmail(memberCreateDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("중복되는 이메일입니다.");
        }

        if (memberCreateDto.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자리 이상으로 설정해주세요.");
        }

        memberMemoryRepository.save(memberCreateDto.entityFromMemberCreatDto());
    }
}
