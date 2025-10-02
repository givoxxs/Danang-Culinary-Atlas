package com.atlasculinary.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID; // Cần import UUID

@Entity
@Table(name = "restaurant_tag_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RestaurantTagMapId.class)
public class RestaurantTagMap {

    @Id
    @Column(name = "restaurant_id", columnDefinition = "UUID")
    private UUID restaurantId;

    @Id
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private RestaurantTag restaurantTag;
}