package com.ramm.lms.Controller;

import com.ramm.lms.DTO.Response.CourseResponse;
import com.ramm.lms.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student/courses")
@RequiredArgsConstructor

public class StudentCourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getMyCourses() {

        return ResponseEntity.ok(
                courseService.getStudentCourses()
        );
    }
}
