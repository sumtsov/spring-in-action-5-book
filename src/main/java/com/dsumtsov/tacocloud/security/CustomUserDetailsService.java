package com.dsumtsov.tacocloud.security;

import com.dsumtsov.tacocloud.entity.User;
import com.dsumtsov.tacocloud.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (Objects.nonNull(user)) {
            return user;
        } else {
            throw new UsernameNotFoundException("User '" + username + " 'not found");
        }
    }
}
