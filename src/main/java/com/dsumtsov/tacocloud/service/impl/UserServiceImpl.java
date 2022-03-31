package com.dsumtsov.tacocloud.service.impl;

import com.dsumtsov.tacocloud.entity.User;
import com.dsumtsov.tacocloud.repository.jpa.UserRepository;
import com.dsumtsov.tacocloud.security.RegistrationForm;
import com.dsumtsov.tacocloud.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(@NonNull RegistrationForm form) {
        return userRepository.save(form.toUser(passwordEncoder));
    }
}
