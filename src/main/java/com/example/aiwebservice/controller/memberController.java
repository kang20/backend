package com.example.aiwebservice.controller;


import com.example.aiwebservice.data.domain.Member;
import com.example.aiwebservice.data.dto.CreateMemberDTO;
import com.example.aiwebservice.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class memberController {
    private memberService memberservice;


    @Autowired
    public memberController(memberService memberService) {
        this.memberservice = memberService;
    }

    @PostMapping("/member") // 회원가입 요청
    public String create_member(CreateMemberDTO form) {

        return null;
    }
}
