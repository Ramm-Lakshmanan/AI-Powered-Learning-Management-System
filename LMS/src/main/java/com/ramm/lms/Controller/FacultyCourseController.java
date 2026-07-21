package com.ramm.lms.Controller;

import com.ramm.lms.DTO.Request.CreateCourseRequest;
import com.ramm.lms.DTO.Request.UpdateCourseRequest;
import com.ramm.lms.DTO.Response.CourseResponse;
import com.ramm.lms.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty/courses")
@RequiredArgsConstructor

public class FacultyCourseController {
    private final CourseService courseService;

    // Create Course
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(
            @Valid @RequestBody CreateCourseRequest request) {

        return ResponseEntity.ok(courseService.createCourse(request));
    }

    // View My Courses
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getMyCourses() {

        return ResponseEntity.ok(courseService.getMyCourses());
    }

    // View Course by ID
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable String courseId) {

        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    // Update Course
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable String courseId,
            @Valid @RequestBody UpdateCourseRequest request) {

        return ResponseEntity.ok(
                courseService.updateCourse(courseId, request)
        );
    }

    // Delete Course
    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(
            @PathVariable String courseId) {

        courseService.deleteCourse(courseId);

        return ResponseEntity.ok("Course deleted successfully.");
    }

    // Add Students
    @PostMapping("/{courseId}/students")
    public ResponseEntity<CourseResponse> addStudents(
            @PathVariable String courseId,
            @RequestBody List<String> studentEmails) {

        return ResponseEntity.ok(
                courseService.addStudents(courseId, studentEmails)
        );
    }
}
