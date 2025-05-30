package kacper.book_tracker.controller;

import kacper.book_tracker.dto.UpdateUserProfileDto;
import kacper.book_tracker.dto.UserProfileDto;
import kacper.book_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getCurrentUserProfile() {
        return ResponseEntity.ok(userService.getCurrentUserProfile());
    }

    @PutMapping("/me/updateUser")
    public ResponseEntity<UserProfileDto> updateUserProfile(@RequestBody UpdateUserProfileDto updateUserProfileDto) {
        return ResponseEntity.ok(userService.updateUserProfile(updateUserProfileDto));
    }

    @DeleteMapping("/me/deleteUser")
    public ResponseEntity<String> deleteUserProfile() {
        return ResponseEntity.ok(userService.deleteUserProfile());
    }




}
