package kacper.book_tracker.service;

import kacper.book_tracker.dto.RegisterUserDto;
import kacper.book_tracker.entity.User;
import kacper.book_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository repository;

    public String registerNewUserAccount(RegisterUserDto registerUserDto) {
        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(encoder.encode(registerUserDto.getPassword()));
        user.setRole("ROLE_USER");

        repository.save(user);

        return "User registered successfully";

    }
}
