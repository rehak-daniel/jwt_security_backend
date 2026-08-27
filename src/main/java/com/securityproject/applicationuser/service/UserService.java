package com.securityproject.applicationuser.service;

import com.securityproject.applicationuser.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securityproject.applicationuser.model.domain.ApplicationUserEntity;
import com.securityproject.applicationuser.model.dto.registration.UserBasicRegistrationDto;


@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationUserEntity registerBasicUser(UserBasicRegistrationDto user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        } else {
            ApplicationUserEntity newUser = new ApplicationUserEntity();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(newUser);
        }
    }
}
