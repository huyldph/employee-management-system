package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository <Permission, String> {
}
