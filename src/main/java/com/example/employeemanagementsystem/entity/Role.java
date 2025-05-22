package com.example.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MaVaiTro", length = 50)
    private String roleId;

    @Column(name = "TenVaiTro", nullable = false, length = 100)
    private String roleName;

    @Lob
    @Column(name = "MoTa", columnDefinition = "TEXT")
    private String description;

    @Column(name = "IsSystemRole")
    private Boolean isSystemRole = false;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UserAccount> userAccounts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "VaiTro_QuyenHan", // Name of the pivot table in DB
            joinColumns = @JoinColumn(name = "MaVaiTro"), // FK in pivot table for Role
            inverseJoinColumns = @JoinColumn(name = "MaQuyen") // FK in pivot table for Permission
    )
    private Set<Permission> permissions;
}
