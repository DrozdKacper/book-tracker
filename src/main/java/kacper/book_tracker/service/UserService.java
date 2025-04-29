package kacper.book_tracker.service;

import kacper.book_tracker.dto.RegisterUserDto;
import kacper.book_tracker.entity.User;
import kacper.book_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);

        return user.map(UserDetailsImpl::new)
                .orElseThrow(() ->new UsernameNotFoundException("User not found: " + username));
    }


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
