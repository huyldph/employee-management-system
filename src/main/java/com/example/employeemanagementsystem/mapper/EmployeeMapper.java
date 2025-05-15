package com.example.employeemanagementsystem.mapper;

import com.example.employeemanagementsystem.dto.request.RegisterRequest;
import com.example.employeemanagementsystem.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(RegisterRequest registerRequest);
}
