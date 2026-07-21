package com.ramm.lms.DTO.Response;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialResponse {
    private String id;

    private String title;

    private String description;

    private String courseId;

    private String fileName;

    private String fileType;

    private String uploadedBy;

    private LocalDateTime uploadedAt;
}
