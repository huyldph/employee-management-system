package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.dto.ApiResponse;
import com.example.employeemanagementsystem.dto.request.EmployeeRequest;
import com.example.employeemanagementsystem.dto.response.EmployeeResponse;
import com.example.employeemanagementsystem.dto.response.PageResponse;
import com.example.employeemanagementsystem.service.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    EmployeeService employeeService;

    @GetMapping
    ApiResponse<PageResponse<EmployeeResponse>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<EmployeeResponse>>builder()
                .result(employeeService.findAll(page, size))
                .build();
    }

    @GetMapping("/{employeeId}")
    ApiResponse<EmployeeResponse> findById(@PathVariable String employeeId) {
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.findById(employeeId))
                .build();
    }

    @PutMapping("/update")
    ApiResponse<EmployeeResponse> update(@RequestBody EmployeeRequest request) {
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.update(request))
                .build();
    }

    @DeleteMapping("/delete/{employeeId}")
    ApiResponse<Void> delete(@PathVariable String employeeId) {
        employeeService.delete(employeeId);
        return ApiResponse.<Void>builder()
                .result(null)
                .message("Employee deleted successfully")
                .build();
    }

    @PostMapping("/avatar")
    public ApiResponse<EmployeeResponse> uploadAvatar(
            @RequestParam("file") MultipartFile file) {
        try {
            return ApiResponse.<EmployeeResponse>builder()
                    .result(employeeService.uploadAvatar(file))
                    .build();
        } catch (Exception e) {
            return ApiResponse.<EmployeeResponse>builder()
                    .message("Failed to upload avatar: " + e.getMessage())
                    .build();
        }
    }
}
