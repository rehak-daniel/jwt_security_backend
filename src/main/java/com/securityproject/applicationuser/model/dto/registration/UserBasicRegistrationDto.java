package com.securityproject.applicationuser.model.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBasicRegistrationDto {
    
    private String username;
    private String password;
    
}
