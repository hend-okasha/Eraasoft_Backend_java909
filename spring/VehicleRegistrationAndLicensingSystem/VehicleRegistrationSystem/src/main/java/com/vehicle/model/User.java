package com.vehicle.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String nationalId;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Vehicle> vehicles;
}
