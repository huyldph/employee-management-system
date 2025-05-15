package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.request.RegisterRequest;
import com.example.employeemanagementsystem.dto.response.RegisterResponse;
import com.example.employeemanagementsystem.entity.Account;
import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.entity.Role;
import com.example.employeemanagementsystem.exception.AppException;
import com.example.employeemanagementsystem.exception.ErrorCode;
import com.example.employeemanagementsystem.mapper.AccountMapper;
import com.example.employeemanagementsystem.mapper.EmployeeMapper;
import com.example.employeemanagementsystem.repository.AccountRepository;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import com.example.employeemanagementsystem.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService {
    AccountRepository accountRepository;
    EmployeeRepository employeeRepository;
    RoleRepository roleRepository;
    AccountMapper accountMapper;
    EmployeeMapper employeeMapper;
    PasswordEncoder passwordEncoder;

    public RegisterResponse createAccount(RegisterRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        Employee employee = employeeMapper.toEmployee(request);
        employee.setStatus(true);
        employeeRepository.save(employee);

        Role role = roleRepository.findByName("USER");

        Account account = accountMapper.toAccount(request);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(role);
        account.setEmployee(employee);

        return accountMapper.toResponse(accountRepository.save(account));
    }
}
