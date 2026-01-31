package com.tgroup.fastscore.model.read;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MatchNodeDto(
        UUID matchId,
        ParticipantNodeDto participant1,
        ParticipantNodeDto participant2,
        int score1,
        int score2,
        UUID winnerId,
        String status,
        UUID nextMatchId) {
}
