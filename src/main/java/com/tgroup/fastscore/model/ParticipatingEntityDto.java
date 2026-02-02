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

        ParticipatingEntityStatus status,
        Instant createdAt,

        @NotNull
        @NotEmpty
        List<EntityMemberDto> members
) {}
