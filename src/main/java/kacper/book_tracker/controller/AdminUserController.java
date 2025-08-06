package kacper.book_tracker.controller;

import kacper.book_tracker.dto.UserAdminViewDto;
import kacper.book_tracker.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;


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
    public ResponseEntity<Page<UserAdminViewDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(adminUserService.getAllUsers(pageable));
    }

    // GET /admin/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserAdminViewDto> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(adminUserService.getUserById(id));
    }

    // DELETE /admin/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        return ResponseEntity.ok(adminUserService.deleteUser(id));
    }
}
