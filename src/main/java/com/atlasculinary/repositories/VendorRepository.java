package com.atlasculinary.repositories;

import com.atlasculinary.entities.VendorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface VendorRepository extends JpaRepository<VendorProfile, UUID> {

    Optional<VendorProfile> findByAccountId(UUID accountId);

    Optional<VendorProfile> findByAccountEmail(String email);
}
