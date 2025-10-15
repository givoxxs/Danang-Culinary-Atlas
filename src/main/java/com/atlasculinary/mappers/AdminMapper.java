package com.atlasculinary.mappers;

import com.atlasculinary.dtos.AdminDto;
import com.atlasculinary.entities.AdminProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    @Mapping(source = "account.email", target = "email")
    AdminDto toDto(AdminProfile admin);
    List<AdminDto> toDtoList(List<AdminProfile> adminList);
}
