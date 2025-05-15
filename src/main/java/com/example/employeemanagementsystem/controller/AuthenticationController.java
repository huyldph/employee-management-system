package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.dto.ApiResponse;
import com.example.employeemanagementsystem.dto.request.AuthenticationRequest;
import com.example.employeemanagementsystem.dto.request.RegisterRequest;
import com.example.employeemanagementsystem.dto.response.AuthenticationResponse;
import com.example.employeemanagementsystem.dto.response.RegisterResponse;
import com.example.employeemanagementsystem.service.AccountService;
import com.example.employeemanagementsystem.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    AccountService accountService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/register")
    ApiResponse<RegisterResponse> register(@RequestBody RegisterRequest request) {
        var result = accountService.createAccount(request);
        return ApiResponse.<RegisterResponse>builder()
                .result(result)
                .build();
    }
}
