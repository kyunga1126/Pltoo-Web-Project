package com.pltoo.membership.controller;

import com.pltoo.membership.Service.FriendService;
import com.pltoo.membership.Service.MyPageService;
import com.pltoo.membership.dto.MyPageDTO;
import com.pltoo.membership.entity.FriendEntity;
import com.pltoo.membership.repository.MyPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        try {
            friendService.addFriend(userId, friendId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding friend");
        }
    }
}
