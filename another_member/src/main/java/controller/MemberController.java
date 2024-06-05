package controller;

import dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    //자동적으로 service class에 대한 객체를 주입을 받는다.
    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @GetMapping("/join/join")
    public String saveForm() {
        return "member/join";
    }

    // 스프링에서는 이렇게 더 간단하게 만들 수 있다.
    @PostMapping("/join/join")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save"); //메서드가 제대로 호출되는지 확인
        System.out.println("MemberDTO: " + memberDTO); //필드값이 잘 담겨있는지 확인

        // Concatenate year, month, and day to form the birthDateFormatted string
        String birthDate = memberDTO.getMemberYear() + memberDTO.getMemberMonth() + memberDTO.getMemberDay();
        memberDTO.setBirthDateFormatted(birthDate); // Correctly use the setter method

        System.out.println("Formatted Birth Date: " + memberDTO.getBirthDateFormatted());
        System.out.println(memberDTO.toString());

        memberService.save(memberDTO);
        return "html/nickname";
    }
}