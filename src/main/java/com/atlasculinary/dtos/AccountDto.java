package com.atlasculinary.dtos;

import com.atlasculinary.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AccountDto {

    private UUID accountId;

    private String email;

    private AccountStatus status;

    private String avatarUrl;

    private String fullName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
