package com.gymtrack.gymtrack_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    private boolean isActive = true;

    // Pense à générer les Getters/Setters (Alt+Insert dans IntelliJ)
}