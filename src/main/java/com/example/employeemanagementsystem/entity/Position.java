package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "positions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaChucVu", length = 50)
    private String positionId;

    @Column(name = "TenChucVu", nullable = false, length = 255)
    private String positionName;

    @Lob
    @Column(name = "MoTa", columnDefinition = "TEXT")
    private String description;

    @Column(name = "NgayTao", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "NguoiTao", length = 100)
    private String createdBy;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "oldPosition", fetch = FetchType.LAZY)
    private Set<CareerTransition> oldCareerTransitions;

    @OneToMany(mappedBy = "newPosition", fetch = FetchType.LAZY)
    private Set<CareerTransition> newCareerTransitions;

    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }
}
