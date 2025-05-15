package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long> {
    Optional<Account> findByUsername(String username);
}