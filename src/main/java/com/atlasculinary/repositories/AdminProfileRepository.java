package com.atlasculinary.repositories;

import com.atlasculinary.entities.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminProfileRepository extends JpaRepository<AdminProfile, UUID> {
    
    @Query("SELECT ap FROM AdminProfile ap " +
           "JOIN FETCH ap.account a " +
           "WHERE a.accountId = :accountId")
    Optional<AdminProfile> findByAccountId(@Param("accountId") UUID accountId);
    
    @Query("SELECT ap FROM AdminProfile ap " +
           "JOIN FETCH ap.account a " +
           "WHERE a.email = :email")
    Optional<AdminProfile> findByAccountEmail(@Param("email") String email);
}