package com.ramm.lms.Repository;

import com.ramm.lms.Entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByFacultyId(String facultyId);

    List<Course> findByStudentIdsContaining(String studentId);

    boolean existsByCourseCode(String courseCode);
}
