package com.example.aiwebservice.service;

import com.example.aiwebservice.data.domain.Member;
import com.example.aiwebservice.data.dto.CreateMemberDTO;
import com.example.aiwebservice.repository.MemorymemberRepository;
import com.example.aiwebservice.repository.memberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;



public class memberServiceTest {

    private memberRepository memberRepository = new MemorymemberRepository();
    private memberService memberService = new memberService(memberRepository);

    @AfterEach
    public void aftereach() {
        MemorymemberRepository memorymemberRepository = (MemorymemberRepository) memberRepository;
        memorymemberRepository.Clear();
    }

    @Test
    public void 회원가입_테스트() {
        CreateMemberDTO form = new CreateMemberDTO();
        form.setId("Id");
        form.setName("name");
        form.setPassword("password");
        Member member = memberService.join(form);
        assertThat(member)
                .isEqualTo(memberRepository.findbyid("Id").get());
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
    @Test
    public void 비밀번호_중복_회원가입_테스트() {
        CreateMemberDTO form = new CreateMemberDTO();
        form.setId("Id");
        form.setName("name");
        form.setPassword("password");

        CreateMemberDTO form2 = new CreateMemberDTO();
        form2.setId("I");
        form2.setName("na");
        form2.setPassword("password");

        memberService.join(form);
        assertThrows(IllegalStateException.class, ()-> {
            memberService.join(form2);
        });
    }




}
