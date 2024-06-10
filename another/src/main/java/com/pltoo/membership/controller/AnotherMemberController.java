package com.pltoo.membership.controller;

import com.pltoo.membership.Service.AnotherMemberService;
import com.pltoo.membership.Service.FriendService;
import com.pltoo.membership.Service.MemberService;
import com.pltoo.membership.dto.GameDTO;
import com.pltoo.membership.dto.MemberDTO;
import com.pltoo.membership.entity.FriendEntity;
import com.pltoo.membership.entity.GameEntity;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.repository.GameRepository;
import com.pltoo.membership.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class AnotherMemberController {
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;
    private final FriendService friendService;

    @Autowired
    public AnotherMemberController(MemberRepository memberRepository, GameRepository gameRepository, FriendService friendService) {
        this.memberRepository = memberRepository;
        this.gameRepository = gameRepository;
        this.friendService = friendService;
    }

    @GetMapping("/{userId}")
    public String getAnotherUserProfile(@PathVariable Long userId, Model model) {
        MemberEntity user = memberRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "friend_profile"; // userProfile.html 뷰를 반환
    }

}