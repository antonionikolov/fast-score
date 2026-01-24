package com.tgroup.fastscore.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ParticipatingEntityDto(
        UUID id,

        @NotNull
        UUID tournamentId,

        @NotNull
        @NotBlank
        String name,
        Integer seed,
        /*
            WAITING: Registered but on the waiting list (tournament is full).
            CONFIRMED: Registered and ready to play.
            ACTIVE: Currently has an ongoing match.
            ELIMINATED: Lost in a knockout bracket.
            WITHDRAWN: Voluntarily left before or during the event.
            DISQUALIFIED: Removed by an official (crucial for integrity).
         */
        String status,
        Instant createdAt,

        @NotNull
        @NotEmpty
        List<EntityMemberDto> members
) {}
