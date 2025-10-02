package com.atlasculinary.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID; // Import UUID

@Entity
@Table(name = "account_role_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AccountRoleMapId.class)
public class AccountRoleMap {

    @Id
    @Column(name = "account_id", columnDefinition = "UUID")
    private UUID accountId;

    @Id
    @Column(name = "role_id", columnDefinition = "UUID")
    private UUID roleId;

    @Column(name = "licensed", nullable = false)
    private Boolean licensed = true;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;
}