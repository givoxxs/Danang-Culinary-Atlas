package com.atlasculinary.mappers;

import com.atlasculinary.dtos.AccountDto;
import com.atlasculinary.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);
}
