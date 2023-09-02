package com.example.aiwebservice.repository;


import com.example.aiwebservice.data.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class memberRepositoryIT_Test {

    @Autowired
    private memberRepository memberRepository;

    @Test
    public void 저장_test() {
        Member member = new Member();
        member.setName("name");
        member.setMemberId("ID");
        member.setMemberPassword("password");
        memberRepository.save(member);
        assertThat(member.getName())
                .isEqualTo(memberRepository.findbyid("ID").get().getName());


    }

    @Test
    public void 조회_test() {
        assertThat(memberRepository.findbyid("아이디").get().getName())
                .isEqualTo(memberRepository.findbypassword("비밀번호!").get().getName());
    }



}
