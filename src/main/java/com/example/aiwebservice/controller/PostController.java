package com.example.aiwebservice.controller;


import com.example.aiwebservice.data.dto.CreateMemberDTO;
import com.example.aiwebservice.data.dto.TokenInfo;
import com.example.aiwebservice.data.dto.login_form;
import com.example.aiwebservice.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {


    private memberService memberservice;

    @Autowired
    public PostController(memberService memberService) {
        this.memberservice = memberService;
    }

    @PostMapping("/signup")// 회원가입 요청
    public String signup_member(CreateMemberDTO form) {
        try {
            memberservice.join(form);
        } catch (IllegalStateException e) {
            return "redirect:/signup";
        }
        return "redirect:/";
    }

    @PostMapping("/login")// 회원가입 요청
    @ResponseBody
    public TokenInfo login( login_form loginForm) {
        TokenInfo tokenInfo = memberservice.login(loginForm);
        return tokenInfo;
    }


}
