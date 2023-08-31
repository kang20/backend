package com.example.aiwebservice.service;

import com.example.aiwebservice.data.domain.Member;
import com.example.aiwebservice.data.dto.CreateMemberDTO;
import com.example.aiwebservice.repository.memberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class memberService {
    private memberRepository memberrepository;

    @Autowired
    public memberService(memberRepository memberrpository) {
        this.memberrepository = memberrpository;
    }

    public Member join(CreateMemberDTO form) throws IllegalStateException{
        Member member = new Member();
        member.setName(form.getName());
        member.setMemberId(form.getId());
        member.setMemberPassword(form.getPassword());
        Duplicate(member);
        memberrepository.save(member);
        return member;

    }
    private void Duplicate(Member member) { // ID와 password 일치 코드
        memberrepository.findbyid(member.getMemberId()).ifPresent(m -> {
            throw new IllegalStateException("이미 있는 ID");
        });
        memberrepository.findbypassword(member.getMemberPassword()).ifPresent( m-> {
            throw new IllegalStateException("이미 있는 password");
        });

    }



}
