package com.securityproject.applicationuser.service;

import com.securityproject.applicationuser.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.securityproject.security.service.JwtService; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securityproject.applicationuser.model.domain.ApplicationUserEntity;
import com.securityproject.applicationuser.model.dto.registration.UserBasicRegistrationRequestDto;
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
        ApplicationUserEntity user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadCredentialsException("auth.invalid.credentials"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("auth.invalid.credentials");
        }
        
        List<GrantedAuthority> authorities = List.of(
        new SimpleGrantedAuthority(user.getRole().toString())
        );

        UserDetails userDetails = new User(
        user.getUsername(),
        user.getPassword(),
        authorities
        );

        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }


    public ApplicationUserEntity registerBasicUser(UserBasicRegistrationRequestDto user) {
        if (userRepository.existsByUsername(user.username())) {
            throw new IllegalArgumentException("auth.username.already.exists");
        } else {
            ApplicationUserEntity newUser = new ApplicationUserEntity();
            newUser.setUsername(user.username());
            newUser.setPassword(passwordEncoder.encode(user.password()));
            return userRepository.save(newUser);
        }
    }
}
