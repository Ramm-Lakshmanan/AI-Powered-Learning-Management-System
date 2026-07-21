package com.ramm.lms.Controller;

import com.ramm.lms.DTO.Request.UploadMaterialRequest;
import com.ramm.lms.DTO.Response.MaterialResponse;
import com.ramm.lms.Service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/faculty/materials")
@RequiredArgsConstructor
public class FacultyMaterialController {

    private final MaterialService materialService;

    @PostMapping(value = "/{courseId}", consumes = "multipart/form-data")
    public ResponseEntity<MaterialResponse> uploadMaterial(

            @PathVariable String courseId,

            @RequestPart("data")
            UploadMaterialRequest request,

            @RequestPart("file")
            MultipartFile file
    ) {

        return ResponseEntity.ok(
                materialService.uploadMaterial(courseId, request, file)
        );
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<List<MaterialResponse>> getCourseMaterials(
            @PathVariable String courseId) {

        return ResponseEntity.ok(
                materialService.getCourseMaterials(courseId)
        );
    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<String> deleteMaterial(
            @PathVariable String materialId) {

        materialService.deleteMaterial(materialId);

        return ResponseEntity.ok("Material deleted successfully.");
    }

}