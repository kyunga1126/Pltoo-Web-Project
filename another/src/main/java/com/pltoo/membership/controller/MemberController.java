package com.pltoo.membership.controller;


import com.pltoo.membership.Service.GameService;
import com.pltoo.membership.Service.MemberService;
import com.pltoo.membership.Service.MyPageService;
import com.pltoo.membership.dto.GameDTO;
import com.pltoo.membership.dto.MemberDTO;
import com.pltoo.membership.dto.MyPageDTO;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.entity.MyPageEntity;
import com.pltoo.membership.repository.MemberRepository;
import com.pltoo.membership.repository.MyPageRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.Errors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    //자동적으로 service class에 대한 객체를 주입을 받는다.
    private final MemberService memberService;
    private final GameService gameService;
    private final MyPageService myPageService;
    private final MemberRepository memberRepository;

    //회원가입 페이지 출력 요청
    @GetMapping("join/join")
    public String saveForm() {
        return "/html/join";
    }

    // 전달해온 값을 받는다. RequestParam이라는걸 이용해서 담겨온 값을 옮겨 담는다.
    // 파라미터를 String 변수로 받아서 service class로 넘겨주고 service class에서는 repository로 넘겨주고
    // repository에서는 데이터베이스로 넘기는 작업

    //    public String save(@RequestParam("memberEmail") String memberEmail,
//                       @RequestParam("memberPassword") String memberPassword,
//                       @RequestParam("memberName")String memberName,
//                       @RequestParam("memberYear")String memberYear,
//                       @RequestParam("memberMonth")String memberMonth,
//                       @RequestParam("memberDay")String memberDay,
//                       @RequestParam("memberGender")String memberGender,
//                       @RequestParam("memberPno")String memberPno){
//        System.out.println("MemberController.save");
//        System.out.println("memberEmail: " + memberEmail);
//        System.out.println("memberPassword: " + memberPassword);
//        System.out.println("memberName: " + memberName);
//        System.out.println("memberYear: " + memberYear);
//        System.out.println("memberMonth: " + memberMonth);
//        System.out.println("memberDay: " + memberDay);
//        System.out.println("memberGender: " + memberGender);
//        System.out.println("memberPno: " + memberPno);
//        return "index";
//    }

    @PostMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String memberEmail) {
        boolean exists = memberService.emailExists(memberEmail);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @PostMapping("join/join")
    // 스프링에서는 이렇게 더 간단하게 만들 수 있다.
    public String save(@ModelAttribute @Validated MemberDTO memberDTO, Model model, MyPageDTO myPageDTO) {
        log.info("memerDTO={}", memberDTO);
        // Concatenate year, month, and day to form the birthDateFormatted string
        String birthDate = memberDTO.getMemberYear() + memberDTO.getMemberMonth() + memberDTO.getMemberDay();
        memberDTO.setBirthDateFormatted(birthDate); // Correctly use the setter method

        System.out.println("Formatted Birth Date: " + memberDTO.getBirthDateFormatted());
        System.out.println(memberDTO.toString());

        model.addAttribute("memberEmail", memberDTO.getMemberEmail());

        log.info("Email is unique. Proceeding to save member.");
        memberService.save(memberDTO);
        return "redirect:/html/nickname?memberEmail=" + memberDTO.getMemberEmail();
    }

    @GetMapping("html/nickname")
    public String nicknameSave(@ModelAttribute("memberEmail") String memberEmail, Model model) {
        //model.addAttribute("memberEmail", memberEmail);
        return "html/nickname";
    }

    @PostMapping("html/nickname")
    public String updateNickname(@ModelAttribute("memberEmail") String memberEmail, MemberDTO memberDTO, Model model) {
        String memberNickname = memberDTO.getNickname();
        model.addAttribute("memberEmail", memberDTO.getMemberEmail());

        Long memberNum = memberService.findIdByEmail(memberEmail);
        if (memberNum != null) {
            model.addAttribute("memberNum", memberNum);
            System.out.println("MemberNum: " + memberNum);
        } else {
            System.out.println("No member found with email: " + memberEmail);
            return "errorPage"; // 적절한 에러 페이지로 리다이렉트
        }

        try {
            memberService.updateMemberNickname(memberEmail, memberNickname);
            // 닉네임 업데이트가 성공적으로 완료되면, "select_game" 페이지로 리다이렉트합니다.
            return "redirect:/html/select_game?memberEmail=" + memberEmail + "&memberNum=" + memberNum;
        } catch (IllegalArgumentException e) {
            // 에러 처리: 예외가 발생하면 로그를 남기고 에러 페이지나 적절한 메시지를 보여줄 수 있습니다.
            // 로그 기록, 에러 페이지 반환 등
            System.out.println("Error updating nickname: " + e.getMessage());
            return "errorPage"; // 에러 페이지로 리다이렉트
        }
    }

    @GetMapping("html/select_game")
    public String selectGame(@RequestParam("memberEmail") String memberEmail,
                             @RequestParam("memberNum") Long memberNum,
                             Model model) {
        model.addAttribute("memberEmail", memberEmail);
        System.out.println("Received email: " + memberEmail);
        model.addAttribute("memberNum", memberNum);
        System.out.println("Received num: " + memberNum);


        return "html/select_game"; // 정상적인 경우에만 뷰를 반환
    }

    @PostMapping("/html/select_game")
    public String saveGame(@RequestParam("memberEmail") String memberEmail,
                           @RequestParam("memberNum") Long memberNum,
                           @RequestParam Map<String, String> allParams,
                           Model model,
                           HttpSession session) {
        // 메서드 진입 확인 로그
        System.out.println("selectGame method called");
        model.addAttribute("memberEmail", memberEmail);
        System.out.println("Email: " + memberEmail);
        model.addAttribute("memberNum", memberNum);
        System.out.println("memberNum: " + memberNum);

        // memberNum 확인
        if (memberNum == null) {
            System.out.println("No member found with email: " + memberEmail);
            return "errorPage"; // 적절한 에러 페이지로 리다이렉트
        }

        // MemberEntity 가져오기
        Optional<MemberEntity> optionalMember = memberRepository.findById(memberNum);
        if (optionalMember.isPresent()) {
            MemberEntity foundMember = optionalMember.get();

            // 세션에 로그인 이메일 저장
            session.setAttribute("loginEmail", memberEmail);
            log.info("Game Selection for Email: {}", memberEmail);

            // MyPageEntity 처리 로직 추가
            MyPageEntity myPageEntity = myPageService.getOrCreateMyPageEntity(foundMember);
            log.info("MyPageEntity has been saved: {}", myPageEntity);

            // 선택된 게임들을 처리
            allParams.forEach((key, value) -> {
                if (key.startsWith("B") && "on".equals(value)) {
                    GameDTO gameDTO = new GameDTO();
                    gameDTO.setMemberGame(key); // 게임 체크박스의 name 속성을 사용
                    gameDTO.setMemberNum(memberNum);
                    gameService.createGame(gameDTO);
                    log.info("Game saved for member {}: {}", memberNum, key);
                }
            });

            // 성공적으로 저장된 후 리다이렉트할 페이지
            return "html/login_main";
        } else {
            // memberNum이 올바르지 않은 경우 에러 페이지로 리다이렉트
            System.out.println("No member found with memberNum: " + memberNum);
            return "errorPage";
        }
    }
}