package com.ramm.lms.Service;

import com.ramm.lms.DTO.Request.CreateCourseRequest;
import com.ramm.lms.DTO.Request.UpdateCourseRequest;
import com.ramm.lms.DTO.Response.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseResponse createCourse(CreateCourseRequest request);

    List<CourseResponse> getMyCourses();

    CourseResponse getCourseById(String courseId);

    CourseResponse updateCourse(String courseId,
                                UpdateCourseRequest request);

    void deleteCourse(String courseId);

    CourseResponse addStudents(String courseId,
                               List<String> studentIds);

    List<CourseResponse> getStudentCourses();
}
