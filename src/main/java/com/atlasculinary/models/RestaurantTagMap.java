package com.atlasculinary.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "restaurant_tag_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RestaurantTagMapId.class)
public class RestaurantTagMap {
    
    @Id
    @Column(name = "restaurant_id")
    private Integer restaurantId;
    
    @Id
    @Column(name = "tag_id")
    private Integer tagId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
    private Restaurant restaurant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private RestaurantTag restaurantTag;
}