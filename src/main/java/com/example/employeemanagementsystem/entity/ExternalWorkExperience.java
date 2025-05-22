package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ExternalWorkExperience")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalWorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaKinhNghiem")
    private String experienceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien", nullable = false)
    private Employee employee;

    @Column(name = "TenCongTyCu", nullable = false, length = 255)
    private String previousCompanyName;

    @Column(name = "ViTriDamNhiem", length = 100)
    private String jobTitle;

    @Column(name = "NgayBatDauLam", nullable = false)
    private LocalDate startDate;

    @Column(name = "NgayKetThucLam", nullable = false) // Assuming this is required; make nullable if not
    private LocalDate endDate;

    @Lob
    @Column(name = "MoTaCongViecChinh", columnDefinition = "TEXT")
    private String jobDescription;

    @Column(name = "LyDoNghiViec", length = 500)
    private String reasonForLeaving;

    @Column(name = "MucLuongCuoi", precision = 18, scale = 2)
    private BigDecimal finalSalary;

    @Column(name = "ThongTinNguoiThamChieu", length = 500)
    private String referenceInfo;
}
