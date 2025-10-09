package com.atlasculinary.services;

import com.atlasculinary.dtos.profile.*;
import com.atlasculinary.entities.Account;
import com.atlasculinary.entities.UserProfile;
import com.atlasculinary.entities.AdminProfile;
import com.atlasculinary.entities.VendorProfile;
import com.atlasculinary.repositories.AccountRepository;
import com.atlasculinary.repositories.UserProfileRepository;
import com.atlasculinary.repositories.AdminProfileRepository;
import com.atlasculinary.repositories.VendorProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

  private final AccountRepository accountRepository;
  private final UserProfileRepository userProfileRepository;
  private final AdminProfileRepository adminProfileRepository;
  private final VendorProfileRepository vendorProfileRepository;

  @Override
  @Transactional(readOnly = true)
  public UserProfileResponseDto getUserProfile(String email) {
    UserProfile userProfile = userProfileRepository.findByAccountEmail(email)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin người dùng"));

    return UserProfileResponseDto.builder()
            .userId(userProfile.getUserId())
            .email(userProfile.getAccount().getEmail())
            .fullName(userProfile.getAccount().getFullName())
            .avatarUrl(userProfile.getAccount().getAvatarUrl())
            .dob(userProfile.getDob())
            .gender(userProfile.getGender())
            .build();
  }

  @Override
  public UserProfileResponseDto updateUserProfile(String email, UserProfileUpdateDto updateDto) {
    UserProfile userProfile = userProfileRepository.findByAccountEmail(email)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin người dùng"));

    Account account = userProfile.getAccount();
    
    if (updateDto.getFullName() != null) {
      account.setFullName(updateDto.getFullName());
    }
    if (updateDto.getAvatarUrl() != null) {
      account.setAvatarUrl(updateDto.getAvatarUrl());
    }
    
    if (updateDto.getDob() != null) {
      userProfile.setDob(updateDto.getDob());
    }
    if (updateDto.getGender() != null) {
      userProfile.setGender(updateDto.getGender());
    }

    accountRepository.save(account);
    UserProfile savedProfile = userProfileRepository.save(userProfile);

    return UserProfileResponseDto.builder()
            .userId(savedProfile.getUserId())
            .email(savedProfile.getAccount().getEmail())
            .fullName(savedProfile.getAccount().getFullName())
            .avatarUrl(savedProfile.getAccount().getAvatarUrl())
            .dob(savedProfile.getDob())
            .gender(savedProfile.getGender())
            .build();
  }

  @Override
  @Transactional(readOnly = true)
  public AdminProfileResponseDto getAdminProfile(String email) {
    AdminProfile adminProfile = adminProfileRepository.findByAccountEmail(email)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin quản trị viên"));

    return AdminProfileResponseDto.builder()
            .adminId(adminProfile.getAdminId())
            .email(adminProfile.getAccount().getEmail())
            .fullName(adminProfile.getAccount().getFullName())
            .avatarUrl(adminProfile.getAccount().getAvatarUrl())
            .phone(adminProfile.getPhone())
            .roleLevel(adminProfile.getRoleLevel())
            .build();
  }

  @Override
  public AdminProfileResponseDto updateAdminProfile(String email, AdminProfileUpdateDto updateDto) {
    AdminProfile adminProfile = adminProfileRepository.findByAccountEmail(email)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin quản trị viên"));

    Account account = adminProfile.getAccount();
    
    if (updateDto.getFullName() != null) {
      account.setFullName(updateDto.getFullName());
    }
    if (updateDto.getAvatarUrl() != null) {
      account.setAvatarUrl(updateDto.getAvatarUrl());
    }
    
    if (updateDto.getPhone() != null) {
      adminProfile.setPhone(updateDto.getPhone());
    }

    accountRepository.save(account);
    AdminProfile savedProfile = adminProfileRepository.save(adminProfile);

    return AdminProfileResponseDto.builder()
            .adminId(savedProfile.getAdminId())
            .email(savedProfile.getAccount().getEmail())
            .fullName(savedProfile.getAccount().getFullName())
            .avatarUrl(savedProfile.getAccount().getAvatarUrl())
            .phone(savedProfile.getPhone())
            .roleLevel(savedProfile.getRoleLevel())
            .build();
  }

  @Override
  @Transactional(readOnly = true)
  public VendorProfileResponseDto getVendorProfile(String email) {
    VendorProfile vendorProfile = vendorProfileRepository.findByAccountEmail(email)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin nhà cung cấp"));

    return VendorProfileResponseDto.builder()
            .vendorId(vendorProfile.getVendorId())
            .email(vendorProfile.getAccount().getEmail())
            .fullName(vendorProfile.getAccount().getFullName())
            .avatarUrl(vendorProfile.getAccount().getAvatarUrl())
            .phone(vendorProfile.getPhone())
            .description(vendorProfile.getDescription())
            .businessLicenseNumber(vendorProfile.getBusinessLicense() != null ? 
                vendorProfile.getBusinessLicense().getLicenseNumber() : null)
            .businessLicenseStatus(vendorProfile.getBusinessLicense() != null ? 
                vendorProfile.getBusinessLicense().getApprovalStatus().name() : null)
            .build();
  }

  @Override
  public VendorProfileResponseDto updateVendorProfile(String email, VendorProfileUpdateDto updateDto) {
    VendorProfile vendorProfile = vendorProfileRepository.findByAccountEmail(email)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin nhà cung cấp"));

    Account account = vendorProfile.getAccount();
    
    if (updateDto.getFullName() != null) {
      account.setFullName(updateDto.getFullName());
    }
    if (updateDto.getAvatarUrl() != null) {
      account.setAvatarUrl(updateDto.getAvatarUrl());
    }
    
    if (updateDto.getPhone() != null) {
      vendorProfile.setPhone(updateDto.getPhone());
    }
    if (updateDto.getDescription() != null) {
      vendorProfile.setDescription(updateDto.getDescription());
    }

    accountRepository.save(account);
    VendorProfile savedProfile = vendorProfileRepository.save(vendorProfile);

    return VendorProfileResponseDto.builder()
            .vendorId(savedProfile.getVendorId())
            .email(savedProfile.getAccount().getEmail())
            .fullName(savedProfile.getAccount().getFullName())
            .avatarUrl(savedProfile.getAccount().getAvatarUrl())
            .phone(savedProfile.getPhone())
            .description(savedProfile.getDescription())
            .businessLicenseNumber(savedProfile.getBusinessLicense() != null ? 
                savedProfile.getBusinessLicense().getLicenseNumber() : null)
            .businessLicenseStatus(savedProfile.getBusinessLicense() != null ? 
                savedProfile.getBusinessLicense().getApprovalStatus().name() : null)
            .build();
  }
}