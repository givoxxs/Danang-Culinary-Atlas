package com.atlasculinary.services.impl;

import com.atlasculinary.dtos.VendorDto;
import com.atlasculinary.exceptions.ResourceNotFoundException;
import com.atlasculinary.mappers.VendorMapper;
import com.atlasculinary.repositories.VendorRepository;
import com.atlasculinary.services.VendorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorMapper vendorMapper,
                             VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }
    @Override
    @Transactional
    public VendorDto getVendorById(UUID vendorId) {
        var vendor = vendorRepository.findById(vendorId)
                .orElseThrow(()-> new ResourceNotFoundException("Vendor not found with ID: " + vendorId));
        return vendorMapper.toDto(vendor);
    }
}
