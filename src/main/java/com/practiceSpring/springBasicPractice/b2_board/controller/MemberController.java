package com.practiceSpring.springBasicPractice.b2_board.controller;

import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberListRes;
import com.practiceSpring.springBasicPractice.b2_board.domain.dtos.MemberReqDto;
import com.practiceSpring.springBasicPractice.b2_board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

//    홈화면
    @GetMapping("")
    public String home() {
        return "member/member-home";
    }

//    회원목록조회
    @GetMapping("/list")
    public String memberList(Model model) {
        List<MemberListRes> memberListResList = memberService.findAll();
        model.addAttribute("modelList", memberListResList);
        return "/member/member-list";
    }

//    회원상세조회
    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable Long id) {
        System.out.println(id);
        return "/member/member-detail";
    }

//    회원가입
    @GetMapping("/create")
    public String memberCreate() {
        return "/member/member-create";
    }

    @PostMapping("/create")
    public String memberCreatePost(@ModelAttribute MemberReqDto memberReqDto) {
        memberService.save(memberReqDto);
        return "redirect:/members/list";
    }
}
