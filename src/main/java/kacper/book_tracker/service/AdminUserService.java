package kacper.book_tracker.service;

import kacper.book_tracker.dto.UserAdminViewDto;
import kacper.book_tracker.mapper.AdminUserMapper;
import kacper.book_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<UserAdminViewDto> getAllUsers() {

        return adminUserMapper.toDtoList(userRepository.findAll());
    }

    public UserAdminViewDto getUser(int id) {
        return adminUserMapper.toDto(userRepository.findById(id)
                .orElseThrow());
    }

    public String deleteUser(int id) {
        userRepository.deleteById(id);

        return "User deleted successfully " + id;
    }
}
