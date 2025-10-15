package com.atlasculinary.mappers;

import com.atlasculinary.dtos.DistrictDto;
import com.atlasculinary.dtos.ProvinceDto;
import com.atlasculinary.dtos.WardDto;
import com.atlasculinary.entities.District;
import com.atlasculinary.entities.Province;
import com.atlasculinary.entities.Ward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    @Mapping(source = "province.provinceId", target = "provinceId")
    DistrictDto toDistrictDto(District district);

    List<DistrictDto> toDistrictDtoList(List<District> districtList);

    ProvinceDto toProvinceDto(Province province);

    List<ProvinceDto> toProvinceDtoList(List<Province> provinceList);

    @Mapping(source = "district.districtId", target = "districtId")
    WardDto toWardDto(Ward ward);

    List<WardDto> toWardDtoList(List<Ward> wardList);
}
