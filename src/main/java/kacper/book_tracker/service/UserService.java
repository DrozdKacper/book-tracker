package kacper.book_tracker.service;

import kacper.book_tracker.dto.RegisterUserDto;
import kacper.book_tracker.dto.UpdateUserProfileDto;
import kacper.book_tracker.dto.UserProfileDto;
import kacper.book_tracker.entity.User;
import kacper.book_tracker.mapper.UserProfileMapper;
import kacper.book_tracker.repository.UserRepository;
import kacper.book_tracker.security.AuthHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService{

    private final UserRepository repository;
    private final UserProfileMapper userProfileMapper;
    private final AuthHelper authHelper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository,
                       UserProfileMapper userProfileMapper, AuthHelper authHelper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userProfileMapper = userProfileMapper;
        this.authHelper = authHelper;
        this.passwordEncoder = passwordEncoder;
    }




    public UserProfileDto getCurrentUserProfile() {


        return userProfileMapper.toDto(getCurrentUser());

    }

    public UserProfileDto updateUserProfile(UpdateUserProfileDto updatedUser) {

        User user = getCurrentUser();

        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setUsername(updatedUser.getUsername());

        return userProfileMapper.toDto(repository.save(user));


    }

    public String deleteUserProfile() {

        repository.delete(getCurrentUser());

        return "User deleted successfully";

    }

    public User getCurrentUser() {
        String email = authHelper.getCurrentUserEmail();

        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found" + email));
    }



}
