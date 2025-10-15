package com.atlasculinary.dtos;

import com.atlasculinary.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RestaurantStatusUpdateRequest {
    private UUID vendorId;
    private String restaurantName;
    private ApprovalStatus newStatus;
    private String rejectionReason;
}
