package com.example.aiwebservice.repository;

import com.example.aiwebservice.data.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.util.List;
import java.util.Optional;

public class JDBCTemplatememberRepository implements memberRepository {


    private JdbcTemplate jdbcTemplate;


    public JDBCTemplatememberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member) {
        // SQL query를 정의
        String sql = "INSERT INTO member (memberId, memberPassword, name,memberRole) VALUES (?, ?, ?,?)";
        // SQL query 실행
        jdbcTemplate.update(sql, member.getMemberId(), member.getMemberPassword(), member.getName(),member.getRole());
        return member;
    }

    @Override
    public Optional<Member> findbyid(String memberid) {
        String sql = "SELECT * FROM member where memberId=?";
        List<Member> result = jdbcTemplate.query(sql,memberRowMapper(),memberid);

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "SELECT * FROM member where name=?";
        List<Member> result = jdbcTemplate.query(sql, memberRowMapper(), name);
        return result.stream().findAny();
    }


    private RowMapper<Member> memberRowMapper() {
        return (rs,rowNum) ->{
            Member member = new Member();
            member.setName(rs.getString("name"));
            member.setMemberId(rs.getString("memberId"));
            member.setMemberPassword(rs.getString("memberPassword"));
            member.setRole(rs.getString("memberRole"));
            return member;
        };
    }
}
