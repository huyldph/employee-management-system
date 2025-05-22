package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaTaiKhoan")
    private String accountId;

    @Column(name = "TenDangNhap", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "MatKhauHash", nullable = false, length = 255)
    private String passwordHash;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien", unique = true, nullable = false) // Foreign Key column in this table
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaVaiTro") // Foreign Key column in this table
    private Role role;

    @Column(name = "TrangThaiTaiKhoan", length = 50)
    private String accountStatus;

    @Column(name = "NgayTao", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "LanDangNhapCuoi")
    private LocalDateTime lastLoginDate;

    @Column(name = "EmailKichHoat")
    private String activationEmail;

    @Column(name = "MaKichHoat", length = 100)
    private String activationCode;

    @Column(name = "ThoiGianHetHanMaKichHoat")
    private LocalDateTime activationCodeExpiry;

    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }
}
