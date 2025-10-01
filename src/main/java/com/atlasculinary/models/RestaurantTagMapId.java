package com.atlasculinary.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTagMapId implements Serializable {
    
    private Integer restaurantId;
    private Integer tagId;
}