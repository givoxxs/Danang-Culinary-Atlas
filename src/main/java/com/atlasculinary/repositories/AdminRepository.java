package com.atlasculinary.repositories;

import com.atlasculinary.entities.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<AdminProfile, UUID> {

}
