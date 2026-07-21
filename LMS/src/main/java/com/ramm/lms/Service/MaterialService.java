package com.ramm.lms.Service;

import com.ramm.lms.DTO.Request.UploadMaterialRequest;
import com.ramm.lms.DTO.Response.MaterialResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MaterialService {
    MaterialResponse uploadMaterial(String courseId,
                                    UploadMaterialRequest request,
                                    MultipartFile file);

    List<MaterialResponse> getCourseMaterials(String courseId);

    byte[] downloadMaterial(String materialId);

    void deleteMaterial(String materialId);
}
