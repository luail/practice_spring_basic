package com.practiceSpring.springBasicPractice.b2_board.controller;

import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberCreateDto;
import com.practiceSpring.springBasicPractice.b2_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memberPrac2")
public class MemberController {
//    DI(디펜던시 인젝션) 주입 3번
//    memberService를 final로 선언하며, 클래스의 위에 RequiredArgs어노테이션.
    private final MemberService memberService;

//    1. 홈화면
    @GetMapping("")
    public String homepage() {
        return "/member/homepage";
    }

//    2. 회원전체조회
    @GetMapping("/listPage")
    public String memberListPage(Model model) {
        model.addAttribute("memberList" ,memberService.findAll());
        return "/member/listPage";
    }

//    3.회원상세조회
    @GetMapping("/detailPage/{id}")
    public String detailPage(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("member", memberService.findById(id));
            return "/member/detailPage";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/errorPage";
        }
    }

//    4. 회원가입
    @GetMapping("/createPage")
    public String createPage() {
        return ("/member/createPage");
    }

    @PostMapping("/createPage")
    public String createPagePost(@ModelAttribute MemberCreateDto memberCreateDto, Model model) {
        try {
            memberService.save(memberCreateDto);
            return "redirect:/memberPrac2/listPage";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/errorPage";
        }
    }
}
