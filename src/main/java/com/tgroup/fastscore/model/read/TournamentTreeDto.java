package com.tgroup.fastscore.model.read;

import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record TournamentTreeDto(
        UUID tournamentId,
        String tournamentName,
        Instant startsAt,
        String venueNameManual,
        String format,
        Short raceTo,
        boolean handicap,
        String status,
        List<RoundTreeDto> rounds) {
}