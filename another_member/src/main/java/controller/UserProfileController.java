package controller;

import entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserProfileService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired
    private UserProfileService service;

    @GetMapping("/{id}")
    public Optional<UserProfile> getUserProfile(@PathVariable Integer id) {
        return service.getUserProfile(id);
    }

}
