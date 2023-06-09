package com.example.projectstudy1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudyController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("userName", "myName~!");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("userName", "myName~!");
        return "goodbye";
    }
}
