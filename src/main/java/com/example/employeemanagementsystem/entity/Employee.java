package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaNhanVien", length = 50)
    private String employeeId;

    @Column(name = "HoDem", nullable = false, length = 100)
    private String lastNamePrefix;

    @Column(name = "Ten", nullable = false, length = 50)
    private String firstName;

    @Column(name = "HoTenDayDu", length = 155, insertable = false, updatable = false)
    private String fullName;

    @Column(name = "NgaySinh")
    private LocalDate dateOfBirth;

    @Column(name = "GioiTinh", length = 10)
    private String gender;

    @Column(name = "SoCMND_CCCD", unique = true, length = 20)
    private String nationalIdNumber;

    @Column(name = "NgayCapCMND_CCCD")
    private LocalDate nationalIdIssueDate;

    @Column(name = "NoiCapCMND_CCCD", length = 255)
    private String nationalIdIssuePlace;

    @Column(name = "DiaChiThuongTru", length = 500)
    private String permanentAddress;

    @Column(name = "DiaChiTamTru", length = 500)
    private String temporaryAddress;

    @Column(name = "EmailCaNhan", unique = true, length = 255)
    private String personalEmail;

    @Column(name = "SoDienThoaiCaNhan", unique = true, length = 20)
    private String personalPhoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaPhongBan")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChucVu")
    private Position position;

    @Column(name = "NgayVaoLam")
    private LocalDate hireDate;

    @Column(name = "NgayThuViec")
    private LocalDate probationDate;

    @Column(name = "TrangThaiLamViec", length = 50)
    private String employmentStatus;

    @Column(name = "AnhDaiDienURL", length = 500)
    private String avatarUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiQuanLyTrucTiep")
    private Employee manager;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private Set<Employee> subordinates;

    @Column(name = "EmailCongTy", unique = true, nullable = false, length = 255)
    private String companyEmail;

    @Column(name = "SoDienThoaiCongTy", length = 20)
    private String companyPhoneNumber;

    @Column(name = "MaSoThueCaNhan", unique = true, length = 50)
    private String taxIdNumber;

    @Column(name = "SoTaiKhoanNganHang", length = 50)
    private String bankAccountNumber;

    @Column(name = "TenNganHang", length = 100)
    private String bankName;

    @Column(name = "ChiNhanhNganHang", length = 100)
    private String bankBranchName;

    @Column(name = "NgayTaoHoSo", updatable = false)
    private LocalDateTime profileCreatedDate;

    @Column(name = "NguoiTaoHoSo", length = 100)
    private String profileCreatedBy;

    @Column(name = "NgayCapNhatHoSoCuoi")
    private LocalDateTime profileLastUpdatedDate;

    @Column(name = "NguoiCapNhatHoSoCuoi", length = 100)
    private String profileLastUpdatedBy;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private UserAccount userAccount;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LaborContract> laborContracts;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EmployeeDocument> documents;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CareerTransition> careerTransitions;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Education> educations;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ExternalWorkExperience> externalWorkExperiences;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Relative> relatives;

    @PrePersist
    protected void onCreate() {
        if (profileCreatedDate == null) {
            profileCreatedDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        profileLastUpdatedDate = LocalDateTime.now();
    }
}

