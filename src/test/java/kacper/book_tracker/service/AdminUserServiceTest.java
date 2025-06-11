package kacper.book_tracker.service;

import kacper.book_tracker.dto.UserAdminViewDto;
import kacper.book_tracker.entity.User;
import kacper.book_tracker.mapper.AdminUserMapper;
import kacper.book_tracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminUserServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private AdminUserMapper adminUserMapper;

    @InjectMocks AdminUserService adminUserService;

    private UserAdminViewDto adminViewDto;
    private User user;

    @BeforeEach
    void init() {

        user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        adminViewDto = new UserAdminViewDto();
        adminViewDto.setId(1);
        adminViewDto.setUsername("testuser");
        adminViewDto.setEmail("test@example.com");
    }

    @Test
    void getAllUsersShouldReturnUsersSuccessfully() {
        List<User> users = List.of(user);
        List<UserAdminViewDto> dtoList = List.of(adminViewDto);

        when(userRepository.findAll()).thenReturn(users);
        when(adminUserMapper.toDtoList(users)).thenReturn(dtoList);

        List<UserAdminViewDto> result = adminUserService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("testuser", result.get(0).getUsername());

        verify(userRepository, times(1)).findAll();
        verify(adminUserMapper, times(1)).toDtoList(users);
    }

    @Test
    void getUserByIdShouldReturnUserSuccessfully() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(adminUserMapper.toDto(user)).thenReturn(adminViewDto);

        UserAdminViewDto result = adminUserService.getUserById(1);

        assertEquals(1, result.getId());
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());

        verify(userRepository, times(1)).findById(1);
        verify(adminUserMapper, times(1)).toDto(user);
    }


}
