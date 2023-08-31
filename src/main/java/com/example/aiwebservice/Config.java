package com.example.aiwebservice;


import com.example.aiwebservice.repository.MemorymemberRepository;
import com.example.aiwebservice.repository.memberRepository;
import com.example.aiwebservice.service.memberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {



    @Bean
    public memberService memberservice() {
        return new memberService(memberRepository());
    }

    @Bean
    public memberRepository memberRepository() {
        return new MemorymemberRepository();
    }





}
