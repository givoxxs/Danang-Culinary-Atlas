package com.atlasculinary.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRoleMapId implements Serializable {
    
    private Integer accountId;
    private Integer roleId;
}