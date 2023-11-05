package com.example.aiwebservice.repository;

import com.example.aiwebservice.data.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class memoryRepositoryTest {
    private MemorymemberRepository repository = new MemorymemberRepository();

    @AfterEach
    public void aftereach() {
        repository.Clear();
    }
    @Test
    public void 저장() {
        Member member = new Member();
        member.setName("name");
        member.setMemberPassword("password");
        member.setMemberId("Id");
        repository.save(member);
        Assertions.assertThat(repository.findbyid("Id").get())
                .isEqualTo(member);
    }



}
