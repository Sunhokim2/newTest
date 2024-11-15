package com.example.newTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LobyController {
    @GetMapping("/lobby")
    public String lobby(Model model){
        return "lobby"; //lobby.html반환
    }
}
