package com.ramm.lms.Service;

import com.ramm.lms.DTO.Request.LoginRequest;
import com.ramm.lms.DTO.Response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
