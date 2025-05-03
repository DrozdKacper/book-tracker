package kacper.book_tracker.controller;

import kacper.book_tracker.dto.UpdateUserProfileDto;
import kacper.book_tracker.dto.UserProfileDto;
import kacper.book_tracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserProfileDto getCurrentUserProfile() {
        return userService.getCurrentUserProfile();
    }

    @PutMapping("/me/updateUser")
    public UserProfileDto updateUserProfile(@RequestBody UpdateUserProfileDto updateUserProfileDto) {
        return userService.updateUserProfile(updateUserProfileDto);
    }

    @DeleteMapping("/me/deleteUser")
    public String deleteUserProfile() {
        return userService.deleteUserProfile();
    }




}
