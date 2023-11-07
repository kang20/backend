package com.example.aiwebservice.controller;


import com.example.aiwebservice.data.dto.CreateMemberDTO;
import com.example.aiwebservice.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

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



//    @PostMapping("/login")
//    public String login_post() {
//        return "redirect:/nav";
//    }


}
