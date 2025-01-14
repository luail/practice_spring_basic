package com.practiceSpring.springBasicPractice.b2_board.controller;

import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberCreateDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberDetailDto;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberListRes;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberReqDto;
import com.practiceSpring.springBasicPractice.b2_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/pracmember")
@RequiredArgsConstructor
public class MemberController {

//    @Autowired DI1번
//    private MemberService memberService;

//    DI2번 : final로 선언후 생성자로 주입.
//    private final MemberService memberService;
//
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

//    DI3번 : final로 선언 후 클래스 위에 @RequeireArgs 선언.
    private final MemberService memberService;

//   홈화면
    @GetMapping("")
    public String home() {
        return "/member/home";
    }

//    회원목록조회
    @GetMapping("/list")
    public String memberList(Model model) {
        List<MemberListRes> memberListResList = memberService.findAll();
        model.addAttribute("memberList",memberListResList);
        return "/member/list";
    }
//    회원상세조회
    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable Long id, Model model) {
        try {
            MemberDetailDto dto = memberService.findById(id);
            model.addAttribute("member", dto);
            return "/member/detail";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/error";
        }
    }
//    회원가입
    @GetMapping("/create")
    public String memberCreate() {
        return "/member/create";
    }

    @PostMapping("/create")
    public String memberCreatePost(@ModelAttribute MemberCreateDto memberCreateDto, Model model) {
        try {
            memberService.save(memberCreateDto);
            return "redirect:/pracmember/list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/error";
        }

    }
}
