package com.atlasculinary.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "province")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {
    
    @Id
    @Column(name = "province_id")
    private Integer provinceId;
    
    @Column(name = "name", nullable = false)
    private String name;
}