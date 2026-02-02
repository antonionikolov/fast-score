package com.tgroup.fastscore.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

public record TournamentDto(
        UUID id,

        @NotNull
        @NotBlank
        String name,

        @NotNull
        Instant startsAt,

        String venueNameManual,

        @NotNull
        String organiserType,

        String format,
        Short raceTo,
        boolean handicap,

        @NotNull
        TournamentStatus status
) {}
