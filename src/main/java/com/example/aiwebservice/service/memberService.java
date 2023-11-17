package com.example.aiwebservice.service;

import com.example.aiwebservice.JwtTokenProvider.JwtTokenProvider;
import com.example.aiwebservice.data.domain.Member;
import com.example.aiwebservice.data.dto.CreateMemberDTO;
import com.example.aiwebservice.data.dto.TokenInfo;
import com.example.aiwebservice.data.dto.login_form;
import com.example.aiwebservice.repository.memberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public class memberService {
    private memberRepository memberrepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public memberService(memberRepository memberrpository) {
        this.memberrepository = memberrpository;

    }

    public Member join(CreateMemberDTO form) throws IllegalStateException{
        Member member = new Member();
        member.setName(form.getName());
        member.setMemberId(form.getId());
        member.setMemberPassword(passwordEncoder.encode(form.getPassword()));
        member.setRole("ROLE_ADMIN");
        Duplicate(member);
        memberrepository.save(member);
        return member;
    }
    public TokenInfo login(login_form loginForm) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginForm.getId(), loginForm.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }
    private void Duplicate(Member member) { // ID와 password 일치 코드
        memberrepository.findbyid(member.getMemberId()).ifPresent(m -> {
            throw new IllegalStateException("이미 있는 ID");
        });


    }
}
