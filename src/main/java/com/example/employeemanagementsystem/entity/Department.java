package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaPhongBan", length = 50)
    private String departmentId;

    @Column(name = "TenPhongBan", nullable = false, length = 255)
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaPhongBanCha")
    private Department parentDepartment;

    @OneToMany(mappedBy = "parentDepartment", fetch = FetchType.LAZY)
    private Set<Department> childDepartments;

    @Lob
    @Column(name = "MoTa", columnDefinition = "TEXT")
    private String description;

    @Column(name = "NgayTao", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "NguoiTao", length = 100)
    private String createdBy;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "oldDepartment", fetch = FetchType.LAZY)
    private Set<CareerTransition> oldCareerTransitions;

    @OneToMany(mappedBy = "newDepartment", fetch = FetchType.LAZY)
    private Set<CareerTransition> newCareerTransitions;

    // If NgayTao is managed by DB (e.g. DEFAULT CURRENT_TIMESTAMP)
    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }
}
