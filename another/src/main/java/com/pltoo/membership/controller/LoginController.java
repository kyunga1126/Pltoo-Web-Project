package com.pltoo.membership.controller;

import com.pltoo.membership.Service.GameService;
import com.pltoo.membership.Service.LoginService;
import com.pltoo.membership.Service.MemberService;
import com.pltoo.membership.Service.MyPageService;
import com.pltoo.membership.dto.MemberDTO;
import com.pltoo.membership.dto.MyPageDTO;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.entity.MyPageEntity;
import com.pltoo.membership.repository.MemberRepository;
import com.pltoo.membership.repository.MyPageRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final MemberRepository memberRepository;
    private final MyPageService myPageService;
    private final MemberService memberService;
    private MyPageEntity myPageEntity;  // 스프링 컨텍스트로부터 MyPageEntity 빈 주입
    private MyPageRepository myPageRepository;


    @GetMapping("/member/login")
    public String loginForm() {
        return "html/loginForm02";
    }


    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        // Fetch the MemberEntity from the database using the email
        Optional<MemberEntity> optionalMember = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

        MemberDTO loginResult = loginService.login(memberDTO);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            log.info("Login Success");
            log.info(loginResult.getMemberEmail());

            if (optionalMember.isPresent()) {
                MemberEntity foundMember = optionalMember.get();
                // 로그인 시간 업데이트
                foundMember.setLoginAt(LocalDateTime.now());
                // JPA 사용하는 경우 저장
                memberRepository.save(foundMember);
                log.info("로그인 정보가 저장되었습니다");

                // MyPageEntity 처리 로직 추가
//                MyPageEntity myPageEntity = myPageService.getOrCreateMyPageEntity(foundMember);
//                log.info("MyPageEntity has been saved: {}", myPageEntity);
            } else {
                log.warn("데이터베이스에 저장되지 않았습니다.");
            }
            return "html/login_main";
        } else {
            // 로그인 실패
            return "html/loginForm02";
        }
    }


    @GetMapping("/member/myPage")
    public String goMyPage(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model){
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            // 세션에 이메일 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/loginForm02";
        }
        // 이메일 정보를 모델에 추가
        model.addAttribute("email", loginEmail);
        log.info("goProfile Success");
        log.info(loginEmail);
        return "html/my_profile";
    }

    @PostMapping("/member/myPage")
    public String goMyProfile(@ModelAttribute MemberDTO memberDTO,
                              @ModelAttribute MyPageDTO MyPageDTO,
                              HttpSession session, Model model) {
        // 세션에서 이메일 정보 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");
        model.addAttribute("email", loginEmail);
        log.info("PostProfile Success");
        log.info("Login Email: {}", loginEmail);
        log.info("MemberDTO: {}", memberDTO);

        // Pno 업데이트
        if (memberDTO.getMemberPno() != null && !memberDTO.getMemberPno().isEmpty()) {
            memberService.updatePno(loginEmail, memberDTO.getMemberPno());
            log.info("Updated Pno for {}", loginEmail);
        } else {
            log.info("memberPno is null or empty, skipping updatePno");
        }

        // 닉네임 업데이트
        if (memberDTO.getNickname() != null && !memberDTO.getNickname().isEmpty()) {
            memberService.updateMemberNickname(loginEmail, memberDTO.getNickname());
            log.info("Updated Nickname for {}", loginEmail);
        } else {
            log.info("Nickname is null or empty, skipping updateMemberNickname");
        }

        // 마이 프로필 페이지로 리다이렉트
        return "/html/my_profile";
    }

}
