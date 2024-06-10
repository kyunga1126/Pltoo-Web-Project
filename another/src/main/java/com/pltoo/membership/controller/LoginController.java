package com.pltoo.membership.controller;

import ch.qos.logback.core.model.Model;
import com.pltoo.membership.Service.GameService;
import com.pltoo.membership.Service.LoginService;
import com.pltoo.membership.Service.MemberService;
import com.pltoo.membership.dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final GameService gameService;
    private final LoginService loginService;

    @GetMapping("/member/login")
    public String loginForm() {
        return "html/loginForm02";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = loginService.login(memberDTO);
        if(loginResult != null){
            //로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "html/login_main";
        }else{
            //로그인 실패
            return "html/loginForm02";
        }
    }

}
