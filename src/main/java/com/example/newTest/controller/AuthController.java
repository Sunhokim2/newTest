package com.example.newTest.controller;

import com.example.newTest.model.User;
import com.example.newTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /** 로그인 페이지 **/
    @GetMapping("/login")
    public String loginPage() {
        return "login"; //login.html반환
    }
    /** 회원가입 처리 **/
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        // 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return ResponseEntity.ok("User register success!!!");
    }
    /** 로그인 처리 **/
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        return userService.validateUser(user)
                ? ResponseEntity.ok("로그인성공")
                : ResponseEntity.status(401).body("실패");
    }

}
