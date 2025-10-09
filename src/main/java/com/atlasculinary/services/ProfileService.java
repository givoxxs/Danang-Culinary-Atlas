package com.atlasculinary.services;

import com.atlasculinary.dtos.profile.*;

public interface ProfileService {
    
  UserProfileResponseDto getUserProfile(String email);
  UserProfileResponseDto updateUserProfile(String email, UserProfileUpdateDto updateDto);
    
  AdminProfileResponseDto getAdminProfile(String email);
  AdminProfileResponseDto updateAdminProfile(String email, AdminProfileUpdateDto updateDto);
  
  VendorProfileResponseDto getVendorProfile(String email);
  VendorProfileResponseDto updateVendorProfile(String email, VendorProfileUpdateDto updateDto);
}