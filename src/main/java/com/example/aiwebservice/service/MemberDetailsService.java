package com.example.aiwebservice.service;

import com.example.aiwebservice.data.MemberRole;
import com.example.aiwebservice.data.domain.Member;
import com.example.aiwebservice.repository.memberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MemberDetailsService implements UserDetailsService {

    private memberRepository memberRepository;

    public MemberDetailsService(memberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> find_member = this.memberRepository.findbyid(id);
        if (find_member.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        Member member = find_member.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("ROLE_ADMIN".equals(member.getRole())) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }
        return new User(member.getName(), member.getMemberPassword(), authorities);
    }
}
