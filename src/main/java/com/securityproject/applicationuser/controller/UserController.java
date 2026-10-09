package com.securityproject.applicationuser.controller;
import com.securityproject.applicationuser.model.dto.login.AuthResponse;
import com.securityproject.applicationuser.model.dto.login.UserBasicLoginRequestDto;
import com.securityproject.applicationuser.model.dto.registration.UserBasicRegistrationRequestDto;
import com.securityproject.applicationuser.service.UserService;
import com.securityproject.constant.EndpointPath;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(EndpointPath.User.USER__LOGIN)
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody UserBasicLoginRequestDto request) {
        return new ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);
    }



    @PostMapping(EndpointPath.User.USER__REGISTER)
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserBasicRegistrationRequestDto userBasicRegistrationDto) {
        userService.registerBasicUser(userBasicRegistrationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //Test endpoint to get the currently authenticated user's profile
    @GetMapping("/api/user/me")
    public ResponseEntity<String> getMyProfile(Authentication authentication) {
        return new ResponseEntity<>(authentication.getName() + " logged in", HttpStatus.OK);
    }
    
    
}
