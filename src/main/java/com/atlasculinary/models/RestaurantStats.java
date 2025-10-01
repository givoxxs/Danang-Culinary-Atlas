package com.atlasculinary.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant_stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantStats {
    
    @Id
    @Column(name = "stats_id")
    private Integer statsId;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    
    @Column(name = "views", nullable = false)
    private Integer views = 0;
    
    @Column(name = "searches", nullable = false)
    private Integer searches = 0;
    
    @Column(name = "checkins", nullable = false)
    private Integer checkins = 0;
    
    @Column(name = "avg_rating", precision = 3, scale = 2)
    private BigDecimal avgRating;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}