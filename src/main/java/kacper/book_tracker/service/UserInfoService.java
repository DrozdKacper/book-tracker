package kacper.book_tracker.service;

import kacper.book_tracker.entity.User;
import kacper.book_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserInfoService  implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        return user.map(UserDetailsImpl::new)
                .orElseThrow(() ->new UsernameNotFoundException("User not found: " + username));
    }
}
