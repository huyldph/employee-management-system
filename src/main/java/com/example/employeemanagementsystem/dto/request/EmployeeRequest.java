package com.example.employeemanagementsystem.dto.request;

import com.example.employeemanagementsystem.entity.Employee;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {
    Long employeeId;
    String firstName;
    String lastName;
    Employee.Gender gender;
    String email;
    String phone;
    String address;
    LocalDate dateOfBirth;
    String avatarUrl;
    Boolean status;
    Long departmentId;
}
