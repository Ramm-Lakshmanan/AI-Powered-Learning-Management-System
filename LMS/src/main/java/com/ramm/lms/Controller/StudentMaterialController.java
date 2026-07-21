package com.ramm.lms.Controller;

import com.ramm.lms.DTO.Response.MaterialResponse;
import com.ramm.lms.Service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/materials")
@RequiredArgsConstructor
public class StudentMaterialController {

    private final MaterialService materialService;

    @GetMapping("/{courseId}")
    public ResponseEntity<List<MaterialResponse>> getMaterials(
            @PathVariable String courseId) {

        return ResponseEntity.ok(
                materialService.getCourseMaterials(courseId)
        );
    }

    @GetMapping("/download/{materialId}")
    public ResponseEntity<byte[]> downloadMaterial(
            @PathVariable String materialId) {

        byte[] file =
                materialService.downloadMaterial(materialId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=document.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }

}