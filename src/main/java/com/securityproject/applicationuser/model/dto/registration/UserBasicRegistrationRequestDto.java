package com.securityproject.applicationuser.model.dto.registration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserBasicRegistrationRequestDto(
    @NotBlank(message = "{user.username.notblank}")
    @Size(min = 3, max = 20, message = "{user.username.size}")
    String username,

    @NotBlank(message = "{user.password.notblank}")
    @Size(min = 6, message = "{user.password.size}")
    String password
) {
    public UserBasicRegistrationRequestDto {
        if (username != null) {
            username = username.trim();
        }
    }
}
