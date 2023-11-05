package com.example.aiwebservice.service;

import com.example.aiwebservice.data.domain.Member;
import com.example.aiwebservice.data.dto.CreateMemberDTO;
import com.example.aiwebservice.repository.MemorymemberRepository;
import com.example.aiwebservice.repository.memberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class memberServiceTest {

    @Autowired
    private memberService memberService;
    @Autowired
    private memberRepository memberRepository;

    @Test
    public void 회원가입_테스트() {
        CreateMemberDTO form = new CreateMemberDTO();
        form.setId("아이디");
        form.setName("강민기");
        form.setPassword("비밀번호");
        Member member = memberService.join(form);
        assertThat(member.getMemberPassword())
                .isEqualTo(memberRepository.findbyid("아이디").get().getMemberPassword());
    }

    @Test
    public void 아이디_중복_회원가입_테스트() {
        CreateMemberDTO form = new CreateMemberDTO();
        form.setId("Id");
        form.setName("name");
        form.setPassword("password");

        CreateMemberDTO form2 = new CreateMemberDTO();
        form2.setId("Id");
        form2.setName("na");
        form2.setPassword("pass");

        memberService.join(form);
        assertThrows(IllegalStateException.class, ()-> {
            memberService.join(form2);
        });
    }




}
