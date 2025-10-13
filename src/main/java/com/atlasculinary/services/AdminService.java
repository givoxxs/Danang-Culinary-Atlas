package com.atlasculinary.services;

import com.atlasculinary.dtos.AdminDto;

import java.util.List;

public interface AdminService {
    List<AdminDto> getAllAdmins();
}
