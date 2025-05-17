package kacper.book_tracker.controller;

import kacper.book_tracker.dto.UserAdminViewDto;
import kacper.book_tracker.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    // GET /admin/users
    @GetMapping
    public List<UserAdminViewDto> getAllUsers() {
        return adminUserService.getAllUsers();
    }

    // GET /admin/users/{id}
    @GetMapping("/{id}")
    public UserAdminViewDto getUser(@PathVariable int id) {
        return adminUserService.getUser(id);
    }

    // DELETE /admin/users/{id}
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return adminUserService.deleteUser(id);
    }
}
