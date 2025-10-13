package com.atlasculinary.services.impl;

import com.atlasculinary.dtos.*;
import com.atlasculinary.entities.Account;
import com.atlasculinary.entities.AdminProfile;
import com.atlasculinary.entities.Restaurant;
import com.atlasculinary.entities.Ward;
import com.atlasculinary.enums.ApprovalStatus;
import com.atlasculinary.exceptions.InvalidRequestException;
import com.atlasculinary.exceptions.ResourceNotFoundException;
import com.atlasculinary.mappers.RestaurantMapper;
import com.atlasculinary.repositories.*;
import com.atlasculinary.services.NotificationService;
import com.atlasculinary.services.RestaurantService;
import jakarta.transaction.Transactional;
import org.hibernate.metamodel.spi.ValueAccess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private static final Logger LOGGER = Logger.getLogger(RestaurantServiceImpl.class.getName());
    private final RestaurantRepository restaurantRepository;
    private final AccountRepository accountRepository;
    private final WardRepository wardRepository;
    private final RestaurantMapper restaurantMapper;
    private final NotificationService notificationService;

    public RestaurantServiceImpl(
            RestaurantRepository restaurantRepository,
            AccountRepository accountRepository,
            WardRepository wardRepository,
            RestaurantMapper restaurantMapper,
            NotificationService notificationService) {

        this.restaurantRepository = restaurantRepository;
        this.accountRepository = accountRepository;
        this.wardRepository = wardRepository;
        this.restaurantMapper = restaurantMapper;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public RestaurantDto createRestaurant(UUID ownerAccountId, AddRestaurantRequest request) {
        var vendor = accountRepository.findById(ownerAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with ID: " + ownerAccountId));

        var ward = wardRepository.findById(request.getWardId())
                .orElseThrow(() -> new ResourceNotFoundException("Ward not found with ID: " +ownerAccountId));

        Restaurant restaurant = restaurantMapper.toEntity(request);

        restaurant.setOwnerAccount(vendor);
        restaurant.setWard(ward);
        restaurant.setApprovalStatus(ApprovalStatus.PENDING);
        restaurant.setCreatedAt(LocalDateTime.now());

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        notificationService.notifyAdminNewRestaurantSubmission(restaurant.getRestaurantId());
        // 6. Trả về DTO
        return restaurantMapper.toDto(savedRestaurant);
    }

    @Override
    public RestaurantDto getRestaurantById(UUID restaurantId) {
        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID: " + restaurantId));

        return restaurantMapper.toDto(restaurant);
    }

    @Override
    public Page<RestaurantDto> getAllRestaurants(int page, int size, String sortBy, String sortDirection) {

        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC :
                Sort.Direction.ASC;

        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Restaurant> restaurantPage = restaurantRepository.findAll(pageable);

        return restaurantPage.map(restaurantMapper::toDto);
    }


    @Override
    public Page<RestaurantDto> getAllRestaurantsByVendor(UUID vendorId, int page, int size, String sortBy, String sortDirection) {
        if (!accountRepository.existsById(vendorId)) {
            throw new ResourceNotFoundException("Vendor not found with ID: " + vendorId);
        }

        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC :
                Sort.Direction.ASC;

        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Restaurant> restaurantPage = restaurantRepository.findByOwnerAccount_AccountId(vendorId, pageable);

        return restaurantPage.map(restaurantMapper::toDto);
    }

    @Override
    @Transactional
    public RestaurantDto updateRestaurant(UUID restaurantId, UpdateRestaurantRequest request) {
        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID: " + restaurantId));


        restaurantMapper.updateRestaurantFromRequest(request, restaurant);

        var requestWardId = request.getWardId();
        if (requestWardId != null) {
            Ward ward = wardRepository.findById(requestWardId)
                    .orElseThrow(() -> new ResourceNotFoundException("Ward not found with ID: " + requestWardId));

            restaurant.setWard(ward);
        }

        restaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toDto(restaurant);

    }

    @Override
    @Transactional
    public void deleteRestaurant(UUID restaurantId) {
        var restaurant = restaurantRepository.findById(restaurantId)
                        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID: " + restaurantId));

        restaurantRepository.delete(restaurant);
    }

    @Override
    @Transactional
    public RestaurantDto updateApprovalStatus(UUID adminAccountId, UUID restaurantId, UpdateApprovalStatusRequest request) {

        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID: " + restaurantId));

        var requestStatus = request.getStatus();

        if (requestStatus != restaurant.getApprovalStatus()) {

            restaurant.setApprovalStatus(requestStatus);
            restaurant.setApprovedAt(LocalDateTime.now());

            Account admin =accountRepository.getReferenceById(adminAccountId);
            restaurant.setApprovedByAccount(admin);

            if (requestStatus == ApprovalStatus.REJECTED) {
                String rejectionReason = request.getRejectionReason();
                if (rejectionReason == null || rejectionReason.trim().isEmpty()) {
                    throw new InvalidRequestException("Rejection reason is mandatory when status is REJECTED.");
                }

                restaurant.setRejectionReason(rejectionReason);

            } else {
                restaurant.setRejectionReason(null);
            }

            restaurant = restaurantRepository.save(restaurant);
            LOGGER.severe("Cap Nhat Trang Thai " + restaurant.getApprovalStatus());
            notificationService.notifyVendorRestaurantStatusUpdate(new RestaurantStatusUpdateRequest(
                    restaurant.getOwnerAccount().getAccountId(),
                    restaurant.getName(),
                    restaurant.getApprovalStatus(),
                    restaurant.getRejectionReason()
            ));

        }

        return restaurantMapper.toDto(restaurant);
    }
}
