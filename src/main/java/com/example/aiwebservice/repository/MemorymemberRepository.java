package com.example.aiwebservice.repository;

import com.example.aiwebservice.data.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MemorymemberRepository implements memberRepository{

    private HashMap<Integer, Member> db = new HashMap<>();
    private int memberKey = 0;


    @Override
    public Member save(Member member) {
        db.put(++memberKey,member);
        return member;
    }

    @Override
    public Optional<Member> findbyid(String memberid) {
        return db.values().stream()
                .filter(member -> member.getMemberId().equals(memberid))
                .findAny();
    }

    @Override
    public Optional<Member> findbypassword(String memberpassword) {
        return db.values().stream()
                .filter(member -> member.getMemberPassword().equals(memberpassword))
                .findAny();
    }

    public void Clear() {
        db.clear();
    }






}
