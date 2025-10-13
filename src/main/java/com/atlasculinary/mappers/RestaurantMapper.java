package com.atlasculinary.mappers;

import com.atlasculinary.dtos.AddRestaurantRequest;
import com.atlasculinary.dtos.RestaurantDto;
import com.atlasculinary.dtos.UpdateRestaurantRequest;
import com.atlasculinary.entities.Restaurant;
import org.mapstruct.*;

@Mapper(componentModel="spring")
public interface RestaurantMapper {

    @Mapping(source = "ownerAccount.accountId", target = "ownerAccountId")
    @Mapping(source = "ward.wardId", target = "wardId")
    @Mapping(source = "approvedByAccount.accountId", target = "approvedByAccountId")
    RestaurantDto toDto(Restaurant entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRestaurantFromRequest(
            UpdateRestaurantRequest request,
            @MappingTarget Restaurant targetEntity
    );

    Restaurant toEntity(AddRestaurantRequest request);
}
