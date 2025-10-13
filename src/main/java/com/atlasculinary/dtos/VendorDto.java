package com.atlasculinary.dtos;

import com.atlasculinary.enums.AccountStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class VendorDto {

    private UUID vendorId;
    private UUID accountId;
    private String email;
    private String phone;
    private String fullName;
    private AccountStatus status;
    private String avatarUrl;
    private String description;
}