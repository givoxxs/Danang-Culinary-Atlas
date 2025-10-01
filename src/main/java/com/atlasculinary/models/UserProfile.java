package com.atlasculinary.models;

import com.atlasculinary.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    
    @Id
    @Column(name = "user_id")
    private Integer userId;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;
    
    @Column(name = "dob")
    private LocalDate dob;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
}