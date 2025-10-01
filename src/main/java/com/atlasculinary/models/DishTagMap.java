package com.atlasculinary.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "dish_tag_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DishTagMapId.class)
public class DishTagMap {
    
    @Id
    @Column(name = "dish_id")
    private Integer dishId;
    
    @Id
    @Column(name = "tag_id")
    private Integer tagId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", insertable = false, updatable = false)
    private Dish dish;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private DishTag dishTag;
}