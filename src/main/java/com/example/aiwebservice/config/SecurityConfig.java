package com.example.aiwebservice.config;


import com.example.aiwebservice.JwtTokenProvider.JwtTokenProvider;
import com.example.aiwebservice.JwtTokenProvider.filter.JwtAuthenticationFilter;
import com.example.aiwebservice.service.MyDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/signup")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/index.html")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/Build/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher( "/")).permitAll()
                        .anyRequest().authenticated())
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/**")))

                .logout((logout) -> logout // 로그아웃 화면 설정 이렇게 설정하면 로그아웃 mapper를 따로 안 구성해도 됨
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .permitAll()
                )

                // session filter 보다 앞에 실행하게 한다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                // 세션 사용하지 않기 때문에 세션 설정 STATELESS
                .sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        String idForEncode = "bcrypt";
        Map encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_5());
        encoders.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v4_1());
        encoders.put("scrypt@SpringSecurity_v5_8", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_2());
        encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("sha256", new StandardPasswordEncoder());

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
        return passwordEncoder;
    }



}



