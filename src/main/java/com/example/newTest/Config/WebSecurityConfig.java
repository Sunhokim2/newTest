package com.example.newTest.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","auth/**", "/static/**").permitAll() // 비로그인 허용 경로
                        .requestMatchers("/chatrooms/**").authenticated()
                        .anyRequest().authenticated() // 채팅방 접근은 로그인 필요
                )
                .formLogin(form -> form
                        .loginPage("/auth/login") // 로그인 페이지 경로
                        .loginProcessingUrl("/auth/login/process") // 로그인 처리 URL
                        .defaultSuccessUrl("/lobby",true) //로그인 성공시
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout") //로그아웃 처리 URL
                        .logoutSuccessUrl("/auth/login") //로그아웃 후 가는 경로
                        .permitAll()
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}












