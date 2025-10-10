package com.atlasculinary.dtos.profile;

import com.atlasculinary.enums.RoleLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminProfileResponseDto {
  private UUID adminId;
  private String email;
  private String fullName;
  private String avatarUrl;
  private String phone;
  private RoleLevel roleLevel;
}