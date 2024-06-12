package com.pltoo.membership.controller;

import com.pltoo.membership.Service.AnotherMemberService;
import com.pltoo.membership.dto.MyPageDTO;
import com.pltoo.membership.entity.GameEntity;
import com.pltoo.membership.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/members")
public class AnotherMemberController {

    @Autowired
    private AnotherMemberService anotherMemberService;
    @Autowired
    private MemberRepository memberRepository;

    // 사용자의 프로필 정보 조회
    @GetMapping("/{memberEmail}")
    public String getMemberByEmail(@PathVariable String memberEmail, Model model) {
        // 이메일로 사용자의 프로필 정보 가져오기
        MyPageDTO memberProfile = anotherMemberService.getMemberByEmail(memberEmail);

        // 게임 목록을 가져와 모델에 추가
        List<String> games = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("No member found with email: " + memberEmail))
                .getGames().stream()
                .map(GameEntity::getMemberGame)
                .collect(Collectors.toList());

        model.addAttribute("profile", memberProfile);
        model.addAttribute("games", games);

        return "friend_profile";  // friend_profile.html을 반환
    }
}
