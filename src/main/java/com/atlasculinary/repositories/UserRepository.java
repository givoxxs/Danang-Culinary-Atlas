package com.atlasculinary.repositories;

import com.atlasculinary.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, UUID> {
    Optional<UserProfile> findByAccountId(UUID accountId);
    Optional<UserProfile> findByAccountEmail(String email);
}
