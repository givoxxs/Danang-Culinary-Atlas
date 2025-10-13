package com.atlasculinary.services.impl;

import com.atlasculinary.dtos.AdminDto;
import com.atlasculinary.mappers.AdminMapper;
import com.atlasculinary.repositories.AdminRepository;
import com.atlasculinary.services.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    public AdminServiceImpl(AdminRepository adminRepository,
                            AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }
    @Override
    @Transactional
    public List<AdminDto> getAllAdmins() {
        var adminList = adminRepository.findAll();
        return adminMapper.toDtoList(adminList);
    }
}
