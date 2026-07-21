package com.ramm.lms.Service.Impl;

import com.ramm.lms.DTO.Request.UploadMaterialRequest;
import com.ramm.lms.DTO.Response.MaterialResponse;
import com.ramm.lms.Entity.Course;
import com.ramm.lms.Entity.Material;
import com.ramm.lms.Entity.Users;
import com.ramm.lms.Repository.CourseRepository;
import com.ramm.lms.Repository.MaterialRepository;
import com.ramm.lms.Repository.UserRepository;
import com.ramm.lms.Service.MaterialService;
import com.ramm.lms.Util.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    @Override
    public MaterialResponse uploadMaterial(String courseId,
                                           UploadMaterialRequest request,
                                           MultipartFile file) {

        Users faculty = getLoggedInUser();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        if (!course.getFacultyId().equals(faculty.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        if (!"application/pdf".equals(file.getContentType())) {
            throw new RuntimeException("Only PDF files are allowed.");
        }

        String storedFile =
                fileStorageService.saveFile(file);

        Material material = Material.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .courseId(courseId)
                .fileName(storedFile)
                .filePath("uploads/" + storedFile)
                .fileType(file.getContentType())
                .uploadedBy(faculty.getId())
                .uploadedAt(LocalDateTime.now())
                .build();

        Material saved =
                materialRepository.save(material);

        return mapToResponse(saved);
    }

    @Override
    public List<MaterialResponse> getCourseMaterials(String courseId) {

        return materialRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public byte[] downloadMaterial(String materialId) {

        Material material = materialRepository.findById(materialId)
                .orElseThrow(() ->
                        new RuntimeException("Material not found"));

        return fileStorageService.getFile(material.getFileName());
    }

    @Override
    public void deleteMaterial(String materialId) {

        Users faculty = getLoggedInUser();

        Material material = materialRepository.findById(materialId)
                .orElseThrow(() ->
                        new RuntimeException("Material not found"));

        if (!material.getUploadedBy().equals(faculty.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        fileStorageService.deleteFile(material.getFileName());

        materialRepository.delete(material);
    }

    private Users getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    private MaterialResponse mapToResponse(Material material) {

        return MaterialResponse.builder()
                .id(material.getId())
                .title(material.getTitle())
                .description(material.getDescription())
                .courseId(material.getCourseId())
                .fileName(material.getFileName())
                .fileType(material.getFileType())
                .uploadedBy(material.getUploadedBy())
                .uploadedAt(material.getUploadedAt())
                .build();
    }
}