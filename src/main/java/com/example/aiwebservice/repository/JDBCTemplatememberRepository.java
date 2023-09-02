package com.example.aiwebservice.repository;

import com.example.aiwebservice.data.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.util.Optional;

public class JDBCTemplatememberRepository implements memberRepository {


    private JdbcTemplate jdbcTemplate;


    public JDBCTemplatememberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member) {
        // SQL query를 정의
        String sql = "INSERT INTO member (memberId, memberPassword, name) VALUES (?, ?, ?)";
        // SQL query 실행
        jdbcTemplate.update(sql, member.getMemberId(), member.getMemberPassword(), member.getName());
        return member;
    }

    @Override
    public Optional<Member> findbyid(String memberid) {
        String sql = "SELECT * FROM member where memberId=?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sql,memberRowMapper(),memberid)
        );
    }

    @Override
    public Optional<Member> findbypassword(String memberpassword) {
        String sql = "SELECT * FROM member where memberPassword=?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sql,memberRowMapper(),memberpassword)
        );
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs,rowNum) ->{
            Member member = new Member();
            member.setName(rs.getString("name"));
            member.setMemberId(rs.getString("memberId"));
            member.setMemberPassword(rs.getString("memberPassword"));
            return member;
        };
    }
}
