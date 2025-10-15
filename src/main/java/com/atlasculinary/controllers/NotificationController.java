package com.atlasculinary.controllers;

import com.atlasculinary.dtos.*;
import com.atlasculinary.enums.ApprovalStatus;
import com.atlasculinary.enums.NotificationType;
import com.atlasculinary.securities.CustomAccountDetails;
import com.atlasculinary.services.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notification")
@Tag(name = "Notification Management", description = "API for managing notification data")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

//    @Operation(summary = "Send welcome notification for user")
//    @PostMapping("/welcome/{accountId}")
//    ResponseEntity<Void> sendWelcomeNotification(@PathVariable UUID accountId){
//        notificationService.sendWelcomeNotification(accountId);
//        return ResponseEntity.ok().build();
//    }

//    @Operation(summary = "Send password reset request notification for user")
//    @PostMapping("/password-reset-request")
//    ResponseEntity<Void> sendPasswordResetRequest(@RequestBody PasswordResetRequest passwordResetRequest){
//        notificationService.sendPasswordResetRequest(passwordResetRequest);
//        return ResponseEntity.ok().build();
//    }

//    @Operation(summary = "Send new restaurant submission notification for admin")
//    @PostMapping("/admin-submission/{restaurantId}")
//    public ResponseEntity<Void> notifyAdminNewRestaurantSubmission(@PathVariable UUID restaurantId) {
//        notificationService.notifyAdminNewRestaurantSubmission(restaurantId);
//        return ResponseEntity.ok().build();
//    }

//    @Operation(summary = "Send restaurant status notification for vendor")
//    @PostMapping("/vendor-status-update")
//    public ResponseEntity<Void> notifyVendorRestaurantStatusUpdate(@RequestBody RestaurantStatusUpdateRequest restaurantStatusUpdateRequest) {
//        notificationService.notifyVendorRestaurantStatusUpdate(restaurantStatusUpdateRequest);
//        return ResponseEntity.ok().build();
//    }



//    @Operation(summary = "Send system error notification for admin")
//    @PostMapping("/system-error")
//    public ResponseEntity<Void> notifySystemError(@RequestBody SystemErrorRequest systemErrorRequest) {
//        notificationService.notifySystemError(systemErrorRequest);
//        return ResponseEntity.ok().build();
//    }



    @Operation(summary = "Get lisf of notification by recipient Id")
    @GetMapping("/{accountId}")
    @PreAuthorize("hasAuthority('ADMIN') or #accountId == principal.accountId")
    ResponseEntity<Page<NotificationDto>> getNotificationsByRecipientId(@PathVariable UUID accountId,
                                                                        @RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size,
                                                                        @RequestParam(defaultValue = "createdAt") String sortBy,
                                                                        @RequestParam(defaultValue = "desc") String sortDirection,
                                                                        @AuthenticationPrincipal CustomAccountDetails principal) {
        Page<NotificationDto> notificationDtoPage = notificationService.getNotificationsByRecipientId(accountId, page, size, sortBy, sortDirection);

        return ResponseEntity.ok(notificationDtoPage);
    }

    @Operation(summary = "Mask a notification with Id as read")
    @PostMapping("/mark-read/{notificationId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId,
                                           @AuthenticationPrincipal CustomAccountDetails principal) {
        var accessAccountId = principal.getAccountId();
        notificationService.markAsRead(accessAccountId, notificationId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Mask all notification as read for current user")
    @PostMapping("/mark-all-read")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> markAllAsRead(@AuthenticationPrincipal CustomAccountDetails principal) {
        var accessAccountId = principal.getAccountId();
        notificationService.markAllAsRead(accessAccountId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Count unread notification for current user")
    @GetMapping("/count/unread")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Long> getUnreadCount(@AuthenticationPrincipal CustomAccountDetails principal) {
        var accessAccountId = principal.getAccountId();
        Long count = notificationService.getUnreadCount(accessAccountId);
        return ResponseEntity.ok(count);
    }

    @Operation(summary = "Get top 10 unread notification for current user")
    @GetMapping("/top10/unread")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<NotificationDto>> getTop10Unread(@AuthenticationPrincipal CustomAccountDetails principal) {
        var accessAccountId = principal.getAccountId();
        List<NotificationDto> notifications = notificationService.getTop10Unread(accessAccountId);
        return ResponseEntity.ok(notifications);
    }

    @Operation(summary = "ADMIN: Count unread notification for any account")
    @GetMapping("/admin/count/unread/{accountId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> getUnreadCountByAdmin(@PathVariable UUID accountId) {
        Long count = notificationService.getUnreadCount(accountId);
        return ResponseEntity.ok(count);
    }

    @Operation(summary = "ADMIN: Get top 10 unread notification for any account")
    @GetMapping("/admin/top10/unread/{accountId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<NotificationDto>> getTop10UnreadByAdmin(@PathVariable UUID accountId) {
        List<NotificationDto> notifications = notificationService.getTop10Unread(accountId);
        return ResponseEntity.ok(notifications);
    }

    @Operation(summary = "ADMIN: Mark all notification as read for any account")
    @PostMapping("/admin/mark-all-read/{accountId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> markAllAsReadByAdmin(@PathVariable UUID accountId) {
        notificationService.markAllAsRead(accountId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "ADMIN: Delete a notification by ID (CRUD)")
    @DeleteMapping("/{notificationId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "ADMIN: Create and send a specific in-app notification to any user")
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<NotificationDto> createInAppNotification(
            @Valid @RequestBody AddNotificationRequest addNotificationRequest) {

        notificationService.createInAppNotification(addNotificationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
