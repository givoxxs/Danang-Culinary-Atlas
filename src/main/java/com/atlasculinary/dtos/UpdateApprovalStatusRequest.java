package com.atlasculinary.dtos;

import com.atlasculinary.enums.ApprovalStatus;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UpdateApprovalStatusRequest {

    @NotNull(message = "Approval status is required")
    private ApprovalStatus status;

    @NotNull(message = "Admin ID is required")
    private UUID adminId;

    private String rejectionReason;
}