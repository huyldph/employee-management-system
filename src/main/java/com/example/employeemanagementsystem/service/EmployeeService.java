package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.request.EmployeeRequest;
import com.example.employeemanagementsystem.dto.response.EmployeeResponse;
import com.example.employeemanagementsystem.dto.response.PageResponse;
import com.example.employeemanagementsystem.entity.UserAccount;
import com.example.employeemanagementsystem.entity.Department;
import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.exception.AppException;
import com.example.employeemanagementsystem.exception.ErrorCode;
import com.example.employeemanagementsystem.mapper.EmployeeMapper;
import com.example.employeemanagementsystem.repository.AccountRepository;
import com.example.employeemanagementsystem.repository.DepartmentRepository;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeService {
    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;
    AccountRepository accountRepository;
    EmployeeMapper employeeMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public PageResponse<EmployeeResponse> findAll(int page, int size) {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var employeePage = employeeRepository.findAll(pageable);

        var pageList = employeePage.getContent()
                .stream()
                .map(employeeMapper::toEmployeeResponse)
                .toList();

        return PageResponse.<EmployeeResponse>builder()
                .currentPage(page)
                .pageSize(employeePage.getSize())
                .totalPages(employeePage.getTotalPages())
                .totalElements(employeePage.getTotalElements())
                .data(pageList)
                .build();
    }

    public EmployeeResponse findById(String id) {
        return employeeMapper.toEmployeeResponse(employeeRepository.findById(id).orElseThrow());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeResponse update(EmployeeRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        employeeMapper.requestToEmployee(employee, request);
        employee.setEmployeeId(request.getEmployeeId());

        Department department = departmentRepository.findById(request.getDepartmentId()).get();
        employee.setDepartment(department);

        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        employeeRepository.delete(employee);
    }

    public EmployeeResponse uploadAvatar(MultipartFile file) throws IOException {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserAccount userAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Employee employee = userAccount.getEmployee();

        // Tên file duy nhất
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Thư mục lưu ảnh
        String uploadDir = "uploads/avatars/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // Lưu file
        Path filepath = Paths.get(uploadDir + filename);
        Files.write(filepath, file.getBytes());

        // Cập nhật avatarUrl
        String avatarUrl = "/uploads/avatars/" + filename;
        employee.setAvatarUrl(avatarUrl);
        employeeRepository.save(employee);

        return employeeMapper.toEmployeeResponse(employee);
    }
}
