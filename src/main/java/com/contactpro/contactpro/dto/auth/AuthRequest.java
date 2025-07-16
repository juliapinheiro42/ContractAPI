package com.contactpro.contactpro.dto.auth;

import com.contactpro.contactpro.model.Profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String username;
    private String password;
    private Profile profile;
}
