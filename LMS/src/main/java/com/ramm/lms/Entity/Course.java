package com.ramm.lms.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Document(collection = "courses")

public class Course {

    @Id
    private String id;

    private String courseCode;

    private String courseName;

    private String description;

    // Faculty who owns this course
    private String facultyId;

    // Students enrolled in this course
    @Builder.Default
    private List<String> studentIds = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
