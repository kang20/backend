package com.example.aiwebservice.repository;


import com.example.aiwebservice.data.domain.Member;

import java.util.List;
import java.util.Optional;

public interface memberRepository { //member 관리 repository
    Member save(Member member);


    Optional<Member> findbyid(String memberid);


    Optional<Member> findByName(String name);


//    Member findbyname(String name);


//    List<Member> findall();
}
