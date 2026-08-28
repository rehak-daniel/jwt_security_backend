package com.securityproject.applicationuser.controller;
import com.securityproject.applicationuser.model.dto.login.AuthResponse;
import com.securityproject.applicationuser.model.dto.login.UserBasicLoginRequestDto;
import com.securityproject.applicationuser.model.dto.registration.UserBasicRegistrationDto;
import com.securityproject.applicationuser.service.UserService;
import com.securityproject.constant.EndpointPath;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(EndpointPath.User.USER__LOGIN)
    public ResponseEntity<AuthResponse> loginUser(@RequestBody UserBasicLoginRequestDto request) {
        return new ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);
    }



    @PostMapping(EndpointPath.User.USER__REGISTER)
    public ResponseEntity<Void> registerUser(@RequestBody UserBasicRegistrationDto userBasicRegistrationDto) {
        userService.registerBasicUser(userBasicRegistrationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
