package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CareerTransition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareerTransition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaQuaTrinh")
    private String transitionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien", nullable = false)
    private Employee employee;

    @Column(name = "SoQuyetDinh", length = 100)
    private String decisionNumber;

    @Column(name = "NgayQuyetDinh")
    private LocalDate decisionDate;

    @Column(name = "NgayHieuLucThayDoi", nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "LoaiThayDoi", length = 100)
    private String changeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaPhongBanCu")
    private Department oldDepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChucVuCu")
    private Position oldPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaPhongBanMoi")
    private Department newDepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChucVuMoi")
    private Position newPosition;

    @Column(name = "MucLuongCu", precision = 18, scale = 2)
    private BigDecimal oldSalary;

    @Column(name = "MucLuongMoi", precision = 18, scale = 2)
    private BigDecimal newSalary;

    @Lob
    @Column(name = "LyDoThayDoi", columnDefinition = "TEXT")
    private String reasonForChange;

    @Column(name = "FileQuyetDinhURL", length = 500)
    private String decisionFileUrl;
}