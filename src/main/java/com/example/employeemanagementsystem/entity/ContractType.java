package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "ContractType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaLoaiHopDong", length = 50)
    private String contractTypeId;

    @Column(name = "TenLoaiHopDong", nullable = false, length = 255)
    private String typeName;

    @Column(name = "ThoiHanMacDinhThang")
    private Integer defaultDurationMonths;

    @Lob
    @Column(name = "MoTa", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "contractType", fetch = FetchType.LAZY)
    private Set<LaborContract> laborContracts;
}
