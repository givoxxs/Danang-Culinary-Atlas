package com.atlasculinary.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "ward")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ward {
    
    @Id
    @Column(name = "ward_id")
    private Integer wardId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
    
    @Column(name = "name", nullable = false)
    private String name;
}