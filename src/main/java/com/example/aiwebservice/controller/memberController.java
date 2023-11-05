package com.example.aiwebservice.controller;


import com.example.aiwebservice.data.dto.CreateMemberDTO;
import com.example.aiwebservice.data.dto.login_form;
import com.example.aiwebservice.service.MemberDetailsService;
import com.example.aiwebservice.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class memberController {
    @Autowired
    private memberService memberservice;
    @Autowired
    private MemberDetailsService memberSecurityService ;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public memberController(memberService memberService) {
        this.memberservice = memberService;
    }




    @GetMapping("/")
    public String main_page() {
        return "navar";
    }

    @GetMapping("/signup") // 회원 가입 페이지
    public String signup_page() {
        return "/member/signup";
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

    @GetMapping("/login") // 로그인 페이지
    public String login_get(login_form login_form) {

        return "/member/login";
    }
    @GetMapping("/user") // 임시 권한 적용 페이지
    public String user_get(){
        return "user_access";
    }

//    @PostMapping("/login")
//    public String login_post() {
//        return "redirect:/nav";
//    }


}
