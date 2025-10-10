package com.atlasculinary.services.impl;

import com.atlasculinary.dtos.AddRestaurantRequest;
import com.atlasculinary.dtos.RestaurantDto;
import com.atlasculinary.dtos.UpdateApprovalStatusRequest;
import com.atlasculinary.dtos.UpdateRestaurantRequest;
import com.atlasculinary.entities.AdminProfile;
import com.atlasculinary.entities.Restaurant;
import com.atlasculinary.entities.Ward;
import com.atlasculinary.enums.ApprovalStatus;
import com.atlasculinary.exceptions.InvalidRequestException;
import com.atlasculinary.exceptions.ResourceNotFoundException;
import com.atlasculinary.mappers.RestaurantMapper;
import com.atlasculinary.repositories.AdminRepository;
import com.atlasculinary.repositories.RestaurantRepository;
import com.atlasculinary.repositories.VendorRepository;
import com.atlasculinary.repositories.WardRepository;
import com.atlasculinary.services.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final VendorRepository vendorRepository;
    private final WardRepository wardRepository;
    private final AdminRepository adminRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantServiceImpl(
            RestaurantRepository restaurantRepository,
            VendorRepository vendorRepository,
            WardRepository wardRepository,
            AdminRepository adminRepository,
            RestaurantMapper restaurantMapper) {

        this.restaurantRepository = restaurantRepository;
        this.vendorRepository = vendorRepository;
        this.adminRepository = adminRepository;
        this.wardRepository = wardRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public RestaurantDto createRestaurant(AddRestaurantRequest request) {
        return null;
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
        if (!vendorRepository.existsById(vendorId)) {
            throw new ResourceNotFoundException("Vendor not found with ID: " + vendorId);
        }

        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC :
                Sort.Direction.ASC;

        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Restaurant> restaurantPage = restaurantRepository.findByVendor_VendorId(vendorId, pageable);

        return restaurantPage.map(restaurantMapper::toDto);
    }

    @Override
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
    public void deleteRestaurant(UUID restaurantId) {
        var restaurant = restaurantRepository.findById(restaurantId)
                        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID: " + restaurantId));

        restaurantRepository.delete(restaurant);
    }

    @Override
    public RestaurantDto updateApprovalStatus(UUID restaurantId, UpdateApprovalStatusRequest request) {

        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID: " + restaurantId));

        var requestStatus = request.getStatus();

        if (requestStatus != restaurant.getApprovalStatus()) {

            restaurant.setApprovalStatus(requestStatus);
            restaurant.setApprovedAt(LocalDateTime.now());

            AdminProfile admin = adminRepository.getReferenceById(request.getAdminId());
            restaurant.setApprovedBy(admin);

            if (requestStatus == ApprovalStatus.REJECTED) {
                String rejectionReason = request.getRejectionReason();
                if (rejectionReason == null || rejectionReason.trim().isEmpty()) {
                    throw new InvalidRequestException("Rejection reason is mandatory when status is REJECTED.");
                }

                restaurant.setRejectionReason(rejectionReason);

            } else {
                restaurant.setRejectionReason(null);
            }
        }

        restaurant = restaurantRepository.save(restaurant);

        return restaurantMapper.toDto(restaurant);
    }
}
