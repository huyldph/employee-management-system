package com.example.employeemanagementsystem;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        // Nạp file .env
        Dotenv dotenv = Dotenv.load();

        // Đẩy các biến vào System properties để Spring Boot có thể đọc được
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
        System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));
        System.setProperty("JWT_SIGNER_KEY", dotenv.get("JWT_SIGNER_KEY"));

        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

}
