package com.atlasculinary.repositories;

import com.atlasculinary.entities.VendorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VendorProfileRepository extends JpaRepository<VendorProfile, UUID> {
    
    @Query("SELECT vp FROM VendorProfile vp " +
           "JOIN FETCH vp.account a " +
           "LEFT JOIN FETCH vp.businessLicense bl " +
           "WHERE a.accountId = :accountId")
    Optional<VendorProfile> findByAccountId(@Param("accountId") UUID accountId);
    
    @Query("SELECT vp FROM VendorProfile vp " +
           "JOIN FETCH vp.account a " +
           "LEFT JOIN FETCH vp.businessLicense bl " +
           "WHERE a.email = :email")
    Optional<VendorProfile> findByAccountEmail(@Param("email") String email);
}