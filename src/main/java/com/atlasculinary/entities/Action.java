package com.atlasculinary.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UuidGenerator; // Cần import cho UUID tự sinh

import java.util.UUID; // Cần import UUID

@Entity
@Table(name = "action")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action {

    @Id
    @GeneratedValue(generator = "uuid2")
    @UuidGenerator
    @Column(name = "action_id", columnDefinition = "UUID")
    private UUID actionId;

    @Column(name = "action_name", nullable = false, length = 100)
    private String actionName;

    @Column(name = "action_code", nullable = false, unique = true, length = 50)
    private String actionCode;
}