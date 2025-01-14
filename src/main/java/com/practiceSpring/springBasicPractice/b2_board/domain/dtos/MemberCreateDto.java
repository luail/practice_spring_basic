package com.practiceSpring.springBasicPractice.b2_board.domain.dtos;

import com.practiceSpring.springBasicPractice.b2_board.domain.Member;
import com.practiceSpring.springBasicPractice.b2_board.repository.MemberMemoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberCreateDto {
    private String name;
    private String email;
    private String password;

    public Member toEntity() {
        return new Member(MemberMemoryRepository.id, this.name, this.email, this.password);
    }
}
