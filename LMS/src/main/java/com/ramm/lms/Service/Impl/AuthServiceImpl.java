package com.ramm.lms.Service.Impl;

import com.ramm.lms.DTO.Request.LoginRequest;
import com.ramm.lms.DTO.Response.LoginResponse;
import com.ramm.lms.Entity.Users;
import com.ramm.lms.Repository.UserRepository;
import com.ramm.lms.Security.JWT.JWTService;
import com.ramm.lms.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BadCredentialsException("User not found"));

        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());

        return LoginResponse.builder()
                .token(token)
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
