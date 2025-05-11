package kacper.book_tracker.controller;

import kacper.book_tracker.dto.UserAdminViewDto;
import kacper.book_tracker.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping
    public List<UserAdminViewDto> getAllUsers() {
        return adminUserService.getAllUsers();
    }

    @GetMapping
    public UserAdminViewDto getUser(@PathVariable int id) {
        return adminUserService.getUser(id);
    }

    @DeleteMapping
    public String deleteUser(@PathVariable int id) {
        return adminUserService.deleteUser(id);
    }
}
