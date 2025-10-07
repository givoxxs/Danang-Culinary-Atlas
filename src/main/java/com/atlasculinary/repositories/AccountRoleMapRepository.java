package com.atlasculinary.repositories;

import com.atlasculinary.entities.AccountRoleMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRoleMapRepository extends JpaRepository<AccountRoleMap, UUID> {
}
