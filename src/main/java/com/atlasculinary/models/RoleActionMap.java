package com.atlasculinary.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "role_action_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RoleActionMapId.class)
public class RoleActionMap {
    
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    
    @Id
    @Column(name = "action_id")
    private Integer actionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", insertable = false, updatable = false)
    private Action action;
}