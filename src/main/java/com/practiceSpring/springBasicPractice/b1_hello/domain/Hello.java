package com.practiceSpring.springBasicPractice.b1_hello.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Getter
@AllArgsConstructor //모든 매개변수가 있는 생성자.
@NoArgsConstructor  //기본생성자
@Data //Getter,Setter,toString 메서드를 모두 포함하는 어노테이션.
public class Hello {
    private String name;
    private String email;
}
