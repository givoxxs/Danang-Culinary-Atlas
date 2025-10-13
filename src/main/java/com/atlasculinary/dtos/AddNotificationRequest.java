package com.atlasculinary.dtos;

import com.atlasculinary.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AddNotificationRequest {
    private UUID accountId;
    private String title;
    private String message;
    private NotificationType type;
    private String targetUrl;
}