package com.atlasculinary.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID; // Cần import UUID

@Entity
@Table(name = "dish_tag_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DishTagMapId.class)
public class DishTagMap {

    @Id
    @Column(name = "dish_id", columnDefinition = "UUID")
    private UUID dishId;

    @Id
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dishId")
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private DishTag dishTag;
}