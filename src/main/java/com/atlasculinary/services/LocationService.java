package com.atlasculinary.services;

import com.atlasculinary.dtos.ProvinceDto; // DTO đơn giản cho địa lý
import com.atlasculinary.dtos.DistrictDto;
import com.atlasculinary.dtos.WardDto;

import java.util.List;

public interface LocationService {

    List<ProvinceDto> getAllProvinces();

    ProvinceDto getProvinceById(int provinceId);

    List<DistrictDto> getDistrictsByProvince(int provinceId);

    DistrictDto getDistrictById(int districtId);

    List<WardDto> getWardsByDistrict(int districtId);

    WardDto getWardById(int wardId);
}