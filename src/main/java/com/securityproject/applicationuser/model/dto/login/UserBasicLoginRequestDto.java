package com.securityproject.applicationuser.model.dto.login;

import jakarta.validation.constraints.NotBlank;

public record UserBasicLoginRequestDto(
    @NotBlank(message = "{login.username.notblank}")
    String username,

    @NotBlank(message = "{login.password.notblank}")
    String password
) {}
