package com.pltoo.membership.controller;

import com.pltoo.membership.Service.FriendService;
import com.pltoo.membership.entity.FriendEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/mypage")
public class MyPageController {
    private final FriendService friendService;

    @Autowired
    public MyPageController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/friends")
    public ResponseEntity<?> addFriend(@RequestParam Long friendId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        try {
            friendService.addFriend(userId, friendId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding friend");
        }
    }

    @GetMapping("/friends")
    public ResponseEntity<?> getFriends(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        try {
            List<FriendEntity> friends = friendService.getFriends(userId);
            return ResponseEntity.ok(friends);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching friends");
        }
    }
}

