package com.atlasculinary.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID; // Cần import UUID

@Entity
@Table(name = "vendor_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorProfile {

    @Id
    @GeneratedValue(generator = "uuid2")
    @UuidGenerator
    @Column(name = "vendor_id", columnDefinition = "UUID")
    private UUID vendorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", unique = true, nullable = false)
    private Account account;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "business_license_id", unique = true, nullable = false)
    private BusinessLicense businessLicense;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Restaurant> restaurantSet = new java.util.HashSet<>();

    @Column(name = "phone", length = 15, nullable = false)
    private String phone;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}