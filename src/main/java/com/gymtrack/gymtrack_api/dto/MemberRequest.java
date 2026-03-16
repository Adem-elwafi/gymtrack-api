package com.gymtrack.gymtrack_api.dto;

import com.gymtrack.gymtrack_api.model.MembershipType;
import jakarta.validation.constraints.*;

public record MemberRequest(
        @NotBlank(message = "Le prénom est obligatoire")
        String firstName,

        @NotBlank(message = "Le nom est obligatoire")
        String lastName,

        @Email(message = "Email invalide")
        @NotBlank(message = "L'email est obligatoire")
        String email,

        @NotNull(message = "Le type d'abonnement est obligatoire")
        MembershipType membershipType
) {}