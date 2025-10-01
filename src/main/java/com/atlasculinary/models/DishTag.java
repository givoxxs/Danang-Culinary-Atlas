package com.atlasculinary.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "dish_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishTag {
    
    @Id
    @Column(name = "tag_id")
    private Integer tagId;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}