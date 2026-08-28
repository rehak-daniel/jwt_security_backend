package com.securityproject.applicationuser.service;

import com.securityproject.applicationuser.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.securityproject.security.service.JwtService; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securityproject.applicationuser.model.domain.ApplicationUserEntity;
import com.securityproject.applicationuser.model.dto.registration.UserBasicRegistrationDto;
import com.securityproject.applicationuser.model.dto.login.UserBasicLoginRequestDto;
import com.securityproject.applicationuser.model.dto.login.AuthResponse;


@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse loginUser(UserBasicLoginRequestDto request) {
        ApplicationUserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }


    public ApplicationUserEntity registerBasicUser(UserBasicRegistrationDto user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        } else {
            ApplicationUserEntity newUser = new ApplicationUserEntity();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(newUser);
        }
    }
}
