package com.atlasculinary.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.UUID; // Cần import UUID

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTagMapId implements Serializable {

    private UUID restaurantId;
    private Long tagId;
}