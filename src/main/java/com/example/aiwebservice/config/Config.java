package com.example.aiwebservice.config;


import com.example.aiwebservice.repository.JDBCTemplatememberRepository;
import com.example.aiwebservice.repository.memberRepository;
import com.example.aiwebservice.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public Config(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public memberService memberservice() {
        return new memberService(memberRepository());
    }



    @Bean
    public memberRepository memberRepository() {
//        return new MemorymemberRepository();
        return new JDBCTemplatememberRepository(dataSource);
    }





}
