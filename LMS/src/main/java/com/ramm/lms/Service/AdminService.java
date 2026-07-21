package com.ramm.lms.Service;

import com.ramm.lms.DTO.Request.CreateUserRequest;
import com.ramm.lms.DTO.Response.UserResponse;

import java.util.List;

public interface AdminService {
    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(String id);

    void deleteUser(String id);

    UserResponse enableUser(String id);

    UserResponse disableUser(String id);
}
