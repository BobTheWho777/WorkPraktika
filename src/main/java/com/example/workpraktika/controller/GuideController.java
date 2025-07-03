package com.example.workpraktika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuideController {

    @GetMapping("/guide")
    public String showGuidePage(){
        return "guide";
    }

    @GetMapping("/about")
    public String showAboutPage(){
        return "about";
    }
}