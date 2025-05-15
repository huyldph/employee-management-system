package com.example.employeemanagementsystem.config;

import com.example.employeemanagementsystem.constant.PredefinedRole;
import com.example.employeemanagementsystem.entity.Account;
import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.entity.Role;
import com.example.employeemanagementsystem.repository.AccountRepository;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
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
    ApplicationRunner applicationRunner(AccountRepository accountRepository, RoleRepository roleRepository, EmployeeRepository employeeRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (accountRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(PredefinedRole.USER_ROLE)
                        .description("User role")
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .name(PredefinedRole.ADMIN_ROLE)
                        .description("Admin role")
                        .build());

                Employee employee = employeeRepository.save(Employee.builder()
                        .firstName("Admin")
                        .lastName("User")
                        .email("huyldph40152@fpt.edu.vn")
                        .gender(Employee.Gender.FEMALE)
                        .status(true)
                        .build());

                Account account = Account.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .role(adminRole)
                        .employee(employee)
                        .build();
                accountRepository.save(account);
            }
        };
    }
}
