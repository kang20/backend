package com.example.aiwebservice.service;

import com.example.aiwebservice.repository.memberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class memberService {
    private memberRepository memberrepository;

    @Autowired
    public memberService(memberRepository memberrpository) {
        this.memberrepository = memberrpository;
    }


}
