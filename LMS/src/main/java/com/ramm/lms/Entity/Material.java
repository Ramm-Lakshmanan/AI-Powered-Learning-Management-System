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

@Document(collection = "materials")

public class Material {
    @Id
    private String id;

    private String title;

    private String description;

    // Course to which this material belongs
    private String courseId;

    // Original uploaded filename
    private String fileName;

    // Stored file path
    private String filePath;

    // application/pdf
    private String fileType;

    // Faculty ID who uploaded it
    private String uploadedBy;

    private LocalDateTime uploadedAt;
}
