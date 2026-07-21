package com.ramm.lms.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection="users")

public class Users {
    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private Role role;

    private String department;

    private Integer year;

    @Builder.Default
    private boolean enabled = true;

    private LocalDateTime createdAt;
}
