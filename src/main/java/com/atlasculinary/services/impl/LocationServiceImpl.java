package com.atlasculinary.services.impl;

import com.atlasculinary.dtos.DistrictDto;
import com.atlasculinary.dtos.ProvinceDto;
import com.atlasculinary.dtos.WardDto;
import com.atlasculinary.exceptions.ResourceNotFoundException;
import com.atlasculinary.mappers.LocationMapper;
import com.atlasculinary.repositories.DistrictRepository;
import com.atlasculinary.repositories.ProvinceRepository;
import com.atlasculinary.repositories.WardRepository;
import com.atlasculinary.services.LocationService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class LocationServiceImpl implements LocationService {
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;
    private final LocationMapper locationMapper;

    public LocationServiceImpl(ProvinceRepository provinceRepository,
                               DistrictRepository districtRepository,
                               WardRepository wardRepository,
                               LocationMapper locationMapper) {
        this.districtRepository = districtRepository;
        this.provinceRepository = provinceRepository;
        this.wardRepository = wardRepository;
        this.locationMapper = locationMapper;

    }
    @Override
    public List<ProvinceDto> getAllProvinces() {
        var provinceList = provinceRepository.findAll();
        return locationMapper.toProvinceDtoList(provinceList);
    }

    @Override
    public ProvinceDto getProvinceById(int provinceId) {
        var province = provinceRepository.findById(provinceId)
                .orElseThrow(() -> new ResourceNotFoundException("Province not found with ID: " + provinceId));

        return locationMapper.toProvinceDto(province);
    }

    @Override
    public List<DistrictDto> getDistrictsByProvince(int provinceId) {
        if (!provinceRepository.existsById(provinceId)) {
            // Ném ra Exception tùy chỉnh, Spring sẽ bắt và trả về HTTP 404
            throw new ResourceNotFoundException("Province not found with ID: " + provinceId);
        }

        var districtList = districtRepository.findByProvince_ProvinceId(provinceId);
        return locationMapper.toDistrictDtoList(districtList);
    }

    @Override
    public DistrictDto getDistrictById(int districtId) {
        var district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District not found with ID: " + districtId));

        return locationMapper.toDistrictDto(district);
    }

    @Override
    public List<WardDto> getWardsByDistrict(int districtId) {
        if (!districtRepository.existsById(districtId)) {
            // Ném ra Exception tùy chỉnh, Spring sẽ bắt và trả về HTTP 404
            throw new ResourceNotFoundException("District not found with ID: " + districtId);
        }

        var wardList = wardRepository.findByDistrict_DistrictId(districtId);
        return locationMapper.toWardDtoList(wardList);
    }

    @Override
    public WardDto getWardById(int wardId) {
        var ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new ResourceNotFoundException("Ward not found with ID: " + wardId));

        return locationMapper.toWardDto(ward);
    }
}
