package com.gymtrack.gymtrack_api.dto;

import com.gymtrack.gymtrack_api.model.MembershipType;
import java.time.LocalDate;

public record MemberResponse(
        Long id,
        String fullName,
        String email,
        MembershipType membershipType,
        LocalDate registrationDate,
        boolean isActive
) {}