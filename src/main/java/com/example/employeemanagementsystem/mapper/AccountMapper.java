package com.example.employeemanagementsystem.mapper;

import com.example.employeemanagementsystem.dto.request.RegisterRequest;
import com.example.employeemanagementsystem.dto.response.RegisterResponse;
import com.example.employeemanagementsystem.entity.UserAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    UserAccount toAccount(RegisterRequest accountRequest);

    RegisterResponse toResponse(UserAccount userAccount);
}
