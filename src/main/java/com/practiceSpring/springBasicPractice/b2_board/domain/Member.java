package com.practiceSpring.springBasicPractice.b2_board.domain;

import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberDetailDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberReqList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member {
    private Long id;
    private String name;
    private String email;
    private String password;

    public MemberReqList listFromEntity() {
        return new MemberReqList(this.id, this.name, this.email);
    }

    public MemberDetailDto detailFromEntity() {
        return new MemberDetailDto(this.name, this.email, this.password);
    }
}
