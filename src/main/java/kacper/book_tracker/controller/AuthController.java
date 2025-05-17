package kacper.book_tracker.controller;

import kacper.book_tracker.dto.AuthRequestDto;
import kacper.book_tracker.dto.RegisterUserDto;
import kacper.book_tracker.service.AuthService;
import kacper.book_tracker.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterUserDto registerUserDto) {
        return service.registerNewUserAccount(registerUserDto);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword())
        );

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequestDto.getEmail());
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }


}
