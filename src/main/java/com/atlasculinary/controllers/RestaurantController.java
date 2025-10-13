package com.atlasculinary.controllers;

import com.atlasculinary.dtos.AddRestaurantRequest;
import com.atlasculinary.dtos.RestaurantDto;
import com.atlasculinary.dtos.UpdateApprovalStatusRequest;
import com.atlasculinary.dtos.UpdateRestaurantRequest;
import com.atlasculinary.securities.CustomAccountDetails;
import com.atlasculinary.services.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/restaurant")
@Tag(name = "Restaurant Management", description = "API for managing restaurant data")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(summary = "Create a new restaurant")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('VENDOR', 'ADMIN')")
    ResponseEntity<RestaurantDto> createRestaurant(@RequestBody AddRestaurantRequest addRestaurantRequest,
                                                   @AuthenticationPrincipal CustomAccountDetails principal) {
        var ownerAccountId = principal.getAccountId();
        var restaurantDto = restaurantService.createRestaurant(ownerAccountId, addRestaurantRequest);
        return new ResponseEntity<>(restaurantDto,HttpStatus.CREATED);
    }
    @Operation(summary = "Get restaurant details by Id")
    @GetMapping("/{restaurantId}")
    ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable UUID restaurantId) {
        var restaurantDto = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.ok(restaurantDto);
    }

    @Operation(summary = "Update an existing restaurant's details")
    @PatchMapping("/{restaurantId}")
    @PreAuthorize("hasAnyAuthority('VENDOR', 'ADMIN')")
    ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable UUID restaurantId,
                                                   @RequestBody UpdateRestaurantRequest updateRestaurantRequest,
                                                   @AuthenticationPrincipal CustomAccountDetails principal) {
        var accessAccountId = principal.getAccountId();
        var restaurantDto = restaurantService.updateRestaurant(restaurantId, updateRestaurantRequest);
        return ResponseEntity.ok(restaurantDto);

    }

    @Operation(summary = "Delete a restaurant by Id")
    @DeleteMapping("/{restaurantId}")
    @PreAuthorize("hasAnyAuthority('VENDOR', 'ADMIN')")
    ResponseEntity<Void> deleteRestaurant(@PathVariable UUID restaurantId,
                                          @AuthenticationPrincipal CustomAccountDetails principal) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all restaurants with pagination and sorting")
    @GetMapping
    public ResponseEntity<Page<RestaurantDto>> getAllRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection) {

        Page<RestaurantDto> restaurantsPage = restaurantService.getAllRestaurants(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(restaurantsPage);
    }

    @Operation(summary = "Get all restaurants of a vendor")
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<Page<RestaurantDto>> getAllRestaurantsByVendor(
            @PathVariable UUID vendorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @AuthenticationPrincipal CustomAccountDetails principal
            ) {

        Page<RestaurantDto> restaurantsPage = restaurantService.getAllRestaurantsByVendor(vendorId, page, size, sortBy, sortDirection);
        return ResponseEntity.ok(restaurantsPage);
    }

    @Operation(summary = "Update the approval status of a restaurant (Admin only)")
    @PatchMapping("/{restaurantId}/approval")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RestaurantDto> updateApprovalStatus(
            @PathVariable UUID restaurantId,
            @Valid @RequestBody UpdateApprovalStatusRequest request,
            @AuthenticationPrincipal CustomAccountDetails principal) {

        var adminAccountId = principal.getAccountId();
        RestaurantDto updatedRestaurant = restaurantService.updateApprovalStatus(adminAccountId, restaurantId, request);
        return ResponseEntity.ok(updatedRestaurant);
    }



}
