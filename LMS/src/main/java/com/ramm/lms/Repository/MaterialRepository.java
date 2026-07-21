package com.ramm.lms.Repository;

import com.ramm.lms.Entity.Material;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MaterialRepository extends MongoRepository<Material, String> {
    List<Material> findByCourseId(String courseId);
}
