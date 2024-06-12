package com.pltoo.membership.controller;

import com.pltoo.membership.Service.LoginService;
import com.pltoo.membership.Service.MyPageService;
import com.pltoo.membership.dto.MemberDTO;
import com.pltoo.membership.dto.MyPageDTO;
import com.pltoo.membership.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyProfileController {

    private final LoginService loginService;
    private final MemberRepository memberRepository;
    private final MyPageService myPageService;

    @PostMapping("/profile/intro")
    public String intro(@ModelAttribute MyPageDTO myPageDTO, HttpSession session, Model model, MemberDTO memberDTO) {
        String loginEmail = (String) session.getAttribute("loginEmail");

        log.info("MyPageDTO: {}", myPageDTO);
        log.info("MyPage Profile : {}", myPageDTO.getProfile());
        log.info(loginEmail);
        myPageService.saveOrUpdateMyPage(myPageDTO, loginEmail);
        return "/html/my_profile";
    }

}
