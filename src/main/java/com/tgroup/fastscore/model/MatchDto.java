package com.tgroup.fastscore.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record MatchDto(
        UUID id,

        @NotNull
        UUID tournamentId,


        UUID participant1Id,
        UUID participant2Id,
        String participant1Name,
        String participant2Name,
        short score1,
        short score2,
        String status,

        @NotNull
        short roundNumber,

        @NotNull
        short sortOrder,
        UUID nextMatchId,
        Integer nextMatchSlot
) {}