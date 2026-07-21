package com.ramm.lms.Controller;

import com.ramm.lms.DTO.Request.CreateUserRequest;
import com.ramm.lms.DTO.Response.UserResponse;
import com.ramm.lms.Service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")

public class AdminController {

    private final AdminService adminService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.createUser(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(
            @PathVariable String id) {

        return ResponseEntity.ok(adminService.getUserById(id));
    }

    @PutMapping("/users/{id}/enable")
    public ResponseEntity<UserResponse> enableUser(
            @PathVariable String id) {

        return ResponseEntity.ok(adminService.enableUser(id));
    }

    @PutMapping("/users/{id}/disable")
    public ResponseEntity<UserResponse> disableUser(
            @PathVariable String id) {

        return ResponseEntity.ok(adminService.disableUser(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable String id) {

        adminService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully.");
    }
}
