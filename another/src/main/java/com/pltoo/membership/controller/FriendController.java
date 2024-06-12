package com.pltoo.membership.controller;

import com.pltoo.membership.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    @Autowired //FriendService의 메서드 사용
    private FriendService friendService;

    @PostMapping("/add") // 친구 추가 요청 처리
    public ResponseEntity<Map<String, Object>> addFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        try {
            friendService.addFriend(userId, friendId);
            return ResponseEntity.ok(Map.of("success", true, "message", "Friend added successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/list") // 친구 목록 조회 요청 처리
    public ResponseEntity<List<Map<String, String>>> getFriends(@RequestParam Long userId) {
        List<String> friendNicknames = friendService.getFriendNicknames(userId); // 친구들 닉네임 목록 가져오기.
        List<Map<String, String>> friendList = friendNicknames.stream()
                .map(nickname -> Map.of("nickname", nickname)) // 닉네임 목록을 MAP형식으로 변환하여 리스트.
                .collect(Collectors.toList());
        return ResponseEntity.ok(friendList);
    }
}
