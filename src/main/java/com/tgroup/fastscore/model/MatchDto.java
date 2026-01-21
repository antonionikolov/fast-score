package com.tgroup.fastscore.model;

import java.util.UUID;

public record MatchDto(
        UUID id,
        UUID tournamentId,
        UUID participant1Id,
        UUID participant2Id,
        String participant1Name,
        String participant2Name,
        short score1,
        short score2,
        String status,
        short roundNumber,
        short sortOrder,
        UUID nextMatchId,
        Integer nextMatchSlot
) {}