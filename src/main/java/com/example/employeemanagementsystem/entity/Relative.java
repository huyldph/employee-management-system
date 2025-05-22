package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Relative")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Relative {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaNguoiThan")
    private String relativeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien", nullable = false)
    private Employee employee;

    @Column(name = "HoTenNguoiThan", nullable = false, length = 150)
    private String relativeName;

    @Column(name = "MoiQuanHeVoiNhanVien", length = 50)
    private String relationship;

    @Column(name = "NgaySinhNguoiThan")
    private LocalDate dateOfBirth;

    @Column(name = "SoDienThoaiLienHe", length = 20)
    private String contactPhoneNumber;

    @Column(name = "DiaChiLienHe", length = 500)
    private String contactAddress;

    @Column(name = "NgheNghiep", length = 100)
    private String occupation;

    @Column(name = "LaNguoiLienHeKhanCap")
    private Boolean isEmergencyContact = false;

    @Lob
    @Column(name = "GhiChu", columnDefinition = "TEXT")
    private String notes;
}
