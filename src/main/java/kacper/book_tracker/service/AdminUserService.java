package kacper.book_tracker.service;

import kacper.book_tracker.dto.UserAdminViewDto;
import kacper.book_tracker.entity.User;
import kacper.book_tracker.exception.UserNotFoundException;
import kacper.book_tracker.mapper.AdminUserMapper;
import kacper.book_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {
    private final UserRepository userRepository;
    private final AdminUserMapper adminUserMapper;

    @Autowired
    public AdminUserService(UserRepository userRepository, AdminUserMapper adminUserMapper) {
        this.userRepository = userRepository;
        this.adminUserMapper = adminUserMapper;
    }

    public Page<UserAdminViewDto> getAllUsers(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return adminUserMapper.toDtoPage(page);
    }

    public UserAdminViewDto getUserById(int id) {
        return adminUserMapper
                .toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found")));
    }

    public String deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return "User deleted successfully " + id;
    }
}
