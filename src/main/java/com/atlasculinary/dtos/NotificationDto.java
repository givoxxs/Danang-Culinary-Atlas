package com.atlasculinary.dtos;

import com.atlasculinary.enums.NotificationType;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class NotificationDto {
    private Long notificationId;
    private String title;
    private String message;
    private NotificationType type;
    private String targetUrl;
    private Boolean isRead;
    private LocalDateTime createdAt;
}
