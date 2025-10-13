package com.atlasculinary.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator; // Cần thiết
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID; // Cần thiết

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(generator = "uuid2")
    @UuidGenerator
    @Column(name = "review_id", columnDefinition = "UUID")
    private UUID reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_account_id", nullable = false)
    private Account reviewerAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Column(name = "rating", nullable = false)
    @Min(1) @Max(5)
    private Integer rating;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "images", columnDefinition = "json")
    private String[] images;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "vendor_reply", columnDefinition = "TEXT")
    private String vendorReply;

    @Column(name = "replied_at")
    private LocalDateTime repliedAt;
}