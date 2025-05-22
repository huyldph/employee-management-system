package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Education")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaTrinhDoHV")
    private String educationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien", nullable = false)
    private Employee employee;

    @Column(name = "TenTruongDaoTao", nullable = false, length = 255)
    private String institutionName;

    @Column(name = "ChuyenNganh", length = 100)
    private String major;

    @Column(name = "BangCapDatDuoc", length = 100)
    private String degreeObtained;

    @Column(name = "HeDaoTao", length = 50)
    private String educationSystem;

    @Column(name = "NamBatDauHoc")
    private Integer startYear;

    @Column(name = "NamTotNghiep", nullable = false)
    private Integer graduationYear;

    @Column(name = "XepLoaiTotNghiep", length = 50)
    private String graduationGrade;

    @Column(name = "FileBangCapURL", length = 500)
    private String degreeFileUrl;
}