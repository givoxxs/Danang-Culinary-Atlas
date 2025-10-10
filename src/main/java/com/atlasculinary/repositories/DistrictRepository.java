package com.atlasculinary.repositories;

import com.atlasculinary.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findByProvince_ProvinceId(int provinceId);
}
