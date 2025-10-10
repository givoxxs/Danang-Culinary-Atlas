package com.atlasculinary.repositories;

import com.atlasculinary.entities.VendorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendorRepository extends JpaRepository<VendorProfile, UUID> {
}
