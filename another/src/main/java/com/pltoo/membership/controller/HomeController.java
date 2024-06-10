package com.pltoo.membership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        System.out.println("HomeController.index");
        return "index"; // => templates 폴더의 main/index.html을 찾아감
    }
}
