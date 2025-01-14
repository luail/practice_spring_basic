package com.practiceSpring.springBasicPractice.b2_board.domain;

import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberDetailDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberListRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {
    private Long id;
    private String name;
    private String email;
    private String password;

    public MemberListRes listFromEntity () {
        return new MemberListRes(this.id, this.name, this.email);
    }

    public MemberDetailDto detailFromEntity() {
        return new MemberDetailDto(this.name, this.email, this.password);
    }
}
