package com.example.employeemanagementsystem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatusCode.valueOf(500)),
    INVALID_KEY(1001, "Uncategorized error", HttpStatusCode.valueOf(400)),
    USER_EXISTED(1002, "User existed", HttpStatusCode.valueOf(400)),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatusCode.valueOf(400)),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatusCode.valueOf(400)),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatusCode.valueOf(404)),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatusCode.valueOf(401)),
    VALIDATION_ERROR(1008, "Validation error", HttpStatusCode.valueOf(400)),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatusCode.valueOf(403));

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
