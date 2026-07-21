package com.ramm.lms.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UploadMaterialRequest {
    @NotBlank
    private String title;

    private String description;
}
