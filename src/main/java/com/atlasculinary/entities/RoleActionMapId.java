package com.atlasculinary.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleActionMapId implements Serializable {

    private UUID roleId;
    private UUID actionId;
}