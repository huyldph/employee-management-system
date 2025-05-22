package com.example.employeemanagementsystem.config;

import com.example.employeemanagementsystem.constant.PredefinedRole;
import com.example.employeemanagementsystem.entity.Permission;
import com.example.employeemanagementsystem.entity.UserAccount;
import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.entity.Role;
import com.example.employeemanagementsystem.repository.AccountRepository;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import com.example.employeemanagementsystem.repository.PermissionRepository;
import com.example.employeemanagementsystem.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(
            AccountRepository accountRepository, RoleRepository roleRepository,
            EmployeeRepository employeeRepository, PermissionRepository permissionRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (accountRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                Permission read = permissionRepository.save(Permission.builder()
                        .permissionName("READ_EMPLOYEE")
                        .description("Read employee")
                        .build());
                Permission add = permissionRepository.save(Permission.builder()
                        .permissionName("ADD_EMPLOYEE")
                        .description("Add employee")
                        .build());
                Set<Permission> permissions = Set.of(read, add);

                Role adminRole = roleRepository.save(Role.builder()
                        .roleName(PredefinedRole.ADMIN_ROLE)
                        .description("Admin role")
                        .isSystemRole(true)
                        .permissions(permissions)
                        .build());

                Employee employee = employeeRepository.save(Employee.builder()
                        .firstName("Admin")
                        .lastNamePrefix("User")
                        .companyEmail("huyldph40152@fpt.edu.vn")
                        .gender("Nam")
                        .employmentStatus("Đang làm việc")
                        .build());

                UserAccount userAccount = UserAccount.builder()
                        .username(ADMIN_USER_NAME)
                        .passwordHash(passwordEncoder.encode(ADMIN_PASSWORD))
                        .role(adminRole)
                        .employee(employee)
                        .build();
                accountRepository.save(userAccount);
            }
        };
    }
}
