package com.atlasculinary.services;

import com.atlasculinary.dtos.VendorDto;

import java.util.UUID;

public interface VendorService {
    VendorDto getVendorById(UUID vendorId);

}
