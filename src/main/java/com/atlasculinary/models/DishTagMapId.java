package com.atlasculinary.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishTagMapId implements Serializable {
    
    private Integer dishId;
    private Integer tagId;
}