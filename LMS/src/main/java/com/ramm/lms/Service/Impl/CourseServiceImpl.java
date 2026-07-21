package com.ramm.lms.Service.Impl;

import com.ramm.lms.DTO.Request.CreateCourseRequest;
import com.ramm.lms.DTO.Request.UpdateCourseRequest;
import com.ramm.lms.DTO.Response.CourseResponse;
import com.ramm.lms.Entity.Course;
import com.ramm.lms.Entity.Role;
import com.ramm.lms.Entity.Users;
import com.ramm.lms.Repository.CourseRepository;
import com.ramm.lms.Repository.UserRepository;
import com.ramm.lms.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {

        if (courseRepository.existsByCourseCode(request.getCourseCode())) {
            throw new RuntimeException("Course code already exists.");
        }

        Users faculty = getLoggedInUser();

        Course course = Course.builder()
                .courseCode(request.getCourseCode())
                .courseName(request.getCourseName())
                .description(request.getDescription())
                .facultyId(faculty.getId())
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .build();

        Course savedCourse = courseRepository.save(course);

        return mapToResponse(savedCourse);
    }

    @Override
    public List<CourseResponse> getMyCourses() {

        Users faculty = getLoggedInUser();

        return courseRepository.findByFacultyId(faculty.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CourseResponse getCourseById(String courseId) {

        Users faculty = getLoggedInUser();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        if (!course.getFacultyId().equals(faculty.getId())) {
            throw new RuntimeException("You are not allowed to access this course.");
        }

        return mapToResponse(course);
    }

    @Override
    public CourseResponse updateCourse(String courseId,
                                       UpdateCourseRequest request) {

        Users faculty = getLoggedInUser();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        if (!course.getFacultyId().equals(faculty.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setUpdatedAt(java.time.LocalDateTime.now());

        Course updated = courseRepository.save(course);

        return mapToResponse(updated);
    }

    @Override
    public void deleteCourse(String courseId) {

        Users faculty = getLoggedInUser();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        if (!course.getFacultyId().equals(faculty.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        courseRepository.delete(course);
    }

    @Override
    public CourseResponse addStudents(String courseId,
                                      List<String> emails) {

        Users faculty = getLoggedInUser();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        if (!course.getFacultyId().equals(faculty.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        for(String email:emails){
            Users student=userRepository.findByEmail(email).orElseThrow(() ->
                    new RuntimeException("Student not found"));
            if(student.getRole() != Role.STUDENT){
                throw new RuntimeException(email + " is not a student.");
            }

            if(!course.getStudentIds().contains(student.getId())){
                course.getStudentIds().add(student.getId());
            }
        }

        course.setUpdatedAt(java.time.LocalDateTime.now());

        Course updated = courseRepository.save(course);

        return mapToResponse(updated);
    }

    @Override
    public List<CourseResponse> getStudentCourses() {

        Users student = getLoggedInUser();

        return courseRepository.findByStudentIdsContaining(student.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private Users getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    private CourseResponse mapToResponse(Course course) {

        return CourseResponse.builder()
                .id(course.getId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .facultyId(course.getFacultyId())
                .studentIds(course.getStudentIds())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}
