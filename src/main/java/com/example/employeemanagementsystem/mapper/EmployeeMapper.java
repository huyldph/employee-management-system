package com.example.employeemanagementsystem.mapper;

import com.example.employeemanagementsystem.dto.request.EmployeeRequest;
import com.example.employeemanagementsystem.dto.request.RegisterRequest;
import com.example.employeemanagementsystem.dto.response.EmployeeResponse;
import com.example.employeemanagementsystem.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(RegisterRequest registerRequest);

    @Mapping(target = "departmentName", source = "department.departmentName")
    EmployeeResponse toEmployeeResponse(Employee employee);

    void requestToEmployee(@MappingTarget Employee employee, EmployeeRequest employeeRequest);
}
