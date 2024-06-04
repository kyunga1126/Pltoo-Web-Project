package controller;

import entity.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MyPageService;

import java.util.Optional;

@RestController
@RequestMapping("/api/mypage")
public class MyPageController {
    @Autowired
    private MyPageService myPageService;

    @GetMapping("/{id}")
    public Optional<MyPage> getMyPage(@PathVariable Integer id) {
        return myPageService.getMyPage(id);

    }


}
