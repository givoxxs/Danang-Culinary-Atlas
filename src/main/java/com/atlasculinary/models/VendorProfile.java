package com.atlasculinary.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "vendor_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorProfile {
    
    @Id
    @Column(name = "vendor_id")
    private Integer vendorId;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;
    
    @Column(name = "business_name", nullable = false)
    private String businessName;
    
    @Column(name = "phone", nullable = false)
    private String phone;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}