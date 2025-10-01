package com.atlasculinary.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleActionMapId implements Serializable {
    
    private Integer roleId;
    private Integer actionId;
}