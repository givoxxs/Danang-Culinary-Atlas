package com.atlasculinary.dtos.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorProfileResponseDto {
  private UUID vendorId;
  private String email;
  private String fullName;
  private String avatarUrl;
  private String phone;
  private String description;
//  private String businessLicenseNumber;
//  private String businessLicenseStatus;
}