package com.atlasculinary.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "account_role_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AccountRoleMapId.class)
public class AccountRoleMap {
    
    @Id
    @Column(name = "account_id")
    private Integer accountId;
    
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    
    @Column(name = "licensed", nullable = false)
    private Boolean licensed = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}