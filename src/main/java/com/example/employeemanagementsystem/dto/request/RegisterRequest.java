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
public class RegisterRequest {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    LocalDate dateOfBirth;
    Employee.Gender gender;
    Long departmentId;
}
