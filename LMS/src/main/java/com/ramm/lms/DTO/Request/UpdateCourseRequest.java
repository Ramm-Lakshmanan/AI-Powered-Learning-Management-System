package com.ramm.lms.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UpdateCourseRequest {
    @NotBlank
    private String courseName;

    private String description;
}
