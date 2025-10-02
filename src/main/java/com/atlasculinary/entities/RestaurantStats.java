package com.atlasculinary.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "restaurant_stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantStats {

    @Id
    @GeneratedValue(generator = "uuid2")
    @UuidGenerator
    @Column(name = "stats_id", columnDefinition = "UUID")
    private UUID statsId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", unique = true, nullable = false)
    private Restaurant restaurant;

    @Column(name = "views", nullable = false)
    private Long views = 0L;

    @Column(name = "searches", nullable = false)
    private Long searches = 0L;

    @Column(name = "checkins", nullable = false)
    private Long checkins = 0L;

    @Column(name = "avg_rating", precision = 3, scale = 2)
    private BigDecimal avgRating;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}