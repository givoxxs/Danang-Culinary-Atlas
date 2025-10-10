package com.atlasculinary.mappers;

import com.atlasculinary.dtos.RestaurantDto;
import com.atlasculinary.dtos.UpdateRestaurantRequest;
import com.atlasculinary.entities.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface RestaurantMapper {

    @Mapping(source = "vendor.vendorId", target = "vendorId")
    @Mapping(source = "ward.wardId", target = "wardId")
    @Mapping(source = "approvedBy.adminId", target = "approvedById")
    RestaurantDto toDto(Restaurant entity);

    void updateRestaurantFromRequest(
            UpdateRestaurantRequest request,
            @MappingTarget Restaurant targetEntity
    );
}
