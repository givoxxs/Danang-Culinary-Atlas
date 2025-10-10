package com.atlasculinary.entities;

import com.atlasculinary.enums.RoleLevel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "admin_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "account")
@EqualsAndHashCode(exclude = "account")
public class AdminProfile {

    @Id
    @GeneratedValue(generator = "uuid2")
    @UuidGenerator
    @Column(name = "admin_id", columnDefinition = "UUID")
    private UUID adminId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", unique = true, nullable = false)
    private Account account;

    @OneToMany(mappedBy = "processedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Report> reportSet = new java.util.HashSet<>();

    @OneToMany(mappedBy = "approvedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BusinessLicense> businessLicenseSet = new java.util.HashSet<>();

    @OneToMany(mappedBy = "approvedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Restaurant> restaurantSet = new java.util.HashSet<>();

    @OneToMany(mappedBy = "approvedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Dish> dishSet = new java.util.HashSet<>();

    @Column(name = "phone", length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_level", length = 20)
    private RoleLevel roleLevel;
}