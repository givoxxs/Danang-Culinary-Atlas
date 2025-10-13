package com.atlasculinary.dtos;

import com.atlasculinary.enums.RoleLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class AdminDto {
    private UUID adminId;
    private UUID accountId;
    private String email;
    private String fullName;
    private String avatarUrl;
    private String phone;
    private RoleLevel roleLevel;
}