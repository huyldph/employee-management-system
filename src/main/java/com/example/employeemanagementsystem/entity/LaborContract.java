package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "LaborContract")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LaborContract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaHopDong")
    private String contractId;

    @Column(name = "SoHopDong", unique = true, nullable = false, length = 100)
    private String contractNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLoaiHopDong", nullable = false)
    private ContractType contractType;

    @Column(name = "NgayKy")
    private LocalDate signingDate;

    @Column(name = "NgayHieuLuc")
    private LocalDate effectiveDate;

    @Column(name = "NgayHetHan")
    private LocalDate expiryDate;

    @Column(name = "MucLuongCoBan", precision = 18, scale = 2)
    private BigDecimal basicSalary;

    @Column(name = "DonViTienTeLuong", length = 10)
    private String salaryCurrency;

    @Lob
    @Column(name = "NoiDungTomTat", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "FileDinhKemURL", length = 500)
    private String attachmentUrl;

    @Column(name = "TrangThaiHopDong", length = 50)
    private String contractStatus;

    @Column(name = "NguoiKyDaiDienCongTy", length = 100)
    private String companySignatoryName;

    @Column(name = "ChucVuNguoiKyCongTy", length = 100)
    private String companySignatoryPosition;

    @Column(name = "NgayTao", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "NguoiTao", length = 100)
    private String createdBy;

    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
        if (salaryCurrency == null) {
            salaryCurrency = "VND"; // Default value
        }
    }
}
