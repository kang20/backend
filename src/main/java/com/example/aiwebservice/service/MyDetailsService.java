package com.example.aiwebservice.service;

import com.example.aiwebservice.data.domain.Member;
import com.example.aiwebservice.data.vo.MyUserDetail;
import com.example.aiwebservice.repository.memberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
public class MyDetailsService implements UserDetailsService {

    private final memberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findbyid(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        MyUserDetail myUserDetail = new MyUserDetail(member);
        return myUserDetail;
    }
}
