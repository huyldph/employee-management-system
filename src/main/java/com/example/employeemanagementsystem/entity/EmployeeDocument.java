package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "EmployeeDocument")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaTaiLieu")
    private String documentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien", nullable = false)
    private Employee employee;

    @Column(name = "TenTaiLieu", nullable = false, length = 255)
    private String documentName;

    @Column(name = "LoaiTaiLieu", length = 100)
    private String documentType;

    @Column(name = "NgayPhatHanhTaiLieu")
    private LocalDate issueDate;

    @Column(name = "NgayTaiLen", updatable = false)
    private LocalDateTime uploadDate;

    @Column(name = "DuongDanFile", nullable = false, length = 500)
    private String filePath;

    @Lob
    @Column(name = "MoTa", columnDefinition = "TEXT")
    private String description;

    @Column(name = "KichThuocFileKB")
    private Integer fileSizeKB;

    @PrePersist
    protected void onCreate() {
        if (uploadDate == null) {
            uploadDate = LocalDateTime.now();
        }
    }
}
