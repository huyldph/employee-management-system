package com.example.employeemanagementsystem.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {
    String employeeId;
    String firstName;
    String lastName;
    String gender;
    String email;
    String phone;
    String address;
    LocalDate dateOfBirth;
    String avatarUrl;
    Boolean status;
    String departmentId;
}
