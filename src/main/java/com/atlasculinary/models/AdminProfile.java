package com.atlasculinary.models;

import com.atlasculinary.enums.RoleLevel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "admin_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminProfile {
    
    @Id
    @Column(name = "admin_id")
    private Integer adminId;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;
    
    @Column(name = "phone", nullable = false)
    private String phone;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role_level", nullable = false)
    private RoleLevel roleLevel;
}