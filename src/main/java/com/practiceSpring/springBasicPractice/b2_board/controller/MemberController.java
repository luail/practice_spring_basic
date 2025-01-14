package com.practiceSpring.springBasicPractice.b2_board.controller;

import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberCreateDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberReqList;
import com.practiceSpring.springBasicPractice.b2_board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/newprac1")
public class MemberController {

//    DI(의존성 주입) 1번
    @Autowired
    private MemberService memberService;

//    case1. 홈화면
    @GetMapping("")
    public String home() {
        return "/member/home";
    }

//    case2. 회원목록조회
    @GetMapping("/lists")
    public String memberLists(Model model) {
        model.addAttribute("memberList",memberService.findAll());
        return "/member/lists";
    }

//    case3. 회원가입
    @GetMapping("/create")
    public String memberCreate() {
        return "/member/create";
    }

    @PostMapping("/create")
    public String memberCreatePost(MemberCreateDto memberCreateDto, Model model) {
        try {
            memberService.save(memberCreateDto);
            return "redirect:/newprac1/lists";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/error";
        }
    }

//    case4. 회원 상세조회
    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("memberDetail" ,memberService.findById(id));
            return "/member/detail";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage",e.getMessage());
            return "/member/error";
        }
    }
}
