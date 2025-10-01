package com.atlasculinary.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "restaurant_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTag {
    
    @Id
    @Column(name = "tag_id")
    private Integer tagId;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}