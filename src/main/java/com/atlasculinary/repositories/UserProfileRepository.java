package com.atlasculinary.repositories;

import com.atlasculinary.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    
    @Query("SELECT up FROM UserProfile up " +
           "JOIN FETCH up.account a " +
           "WHERE a.accountId = :accountId")
    Optional<UserProfile> findByAccountId(@Param("accountId") UUID accountId);
    
    @Query("SELECT up FROM UserProfile up " +
           "JOIN FETCH up.account a " +
           "WHERE a.email = :email")
    Optional<UserProfile> findByAccountEmail(@Param("email") String email);
}