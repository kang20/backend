package com.example.aiwebservice.service;

import com.example.aiwebservice.data.domain.Member;
import com.example.aiwebservice.data.vo.MyUserDetail;
import com.example.aiwebservice.repository.memberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public class MyDetailsService implements UserDetailsService {

    @Autowired
    private memberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> find_member = this.memberRepository.findbyid(id);
        if (find_member.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        Member member = find_member.get();

        return new MyUserDetail(member);
    }
}
