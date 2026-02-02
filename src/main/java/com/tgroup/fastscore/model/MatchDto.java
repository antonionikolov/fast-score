package com.tgroup.fastscore.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

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
        @With short score1,
        @With short score2,
        @With UUID winnerId,
        @With MatchStatus status,

        @NotNull
        short roundNumber,

        @NotNull
        short sortOrder,
        UUID nextMatchId,
        Integer nextMatchSlot
) {}