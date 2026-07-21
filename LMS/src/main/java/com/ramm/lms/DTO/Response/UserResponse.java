package com.ramm.lms.DTO.Response;

import com.ramm.lms.Entity.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserResponse {
    private String id;

    private String name;

    private String email;

    private String department;

    private Integer year;

    private Role role;

    private boolean enabled;

    private LocalDateTime createdAt;
}
