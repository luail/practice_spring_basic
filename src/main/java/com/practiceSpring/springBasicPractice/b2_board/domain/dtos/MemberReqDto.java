package com.practiceSpring.springBasicPractice.b2_board.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberReqDto {
    private String name;
    private String email;
    private String password;
}
