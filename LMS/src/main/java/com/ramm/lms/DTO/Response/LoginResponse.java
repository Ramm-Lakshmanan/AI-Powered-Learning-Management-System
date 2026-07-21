package com.ramm.lms.DTO.Response;

import com.ramm.lms.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class LoginResponse {
    private String token;
    private String name;
    private Role role;
}
