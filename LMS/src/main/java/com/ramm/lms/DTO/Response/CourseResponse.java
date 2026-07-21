package com.ramm.lms.DTO.Response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CourseResponse {
    private String id;

    private String courseCode;

    private String courseName;

    private String description;

    private String facultyId;

    private List<String> studentIds;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
