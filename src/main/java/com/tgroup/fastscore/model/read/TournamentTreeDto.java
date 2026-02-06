package com.tgroup.fastscore.model.read;

import com.tgroup.fastscore.model.TournamentFormat;
import com.tgroup.fastscore.model.TournamentStatus;
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
        TournamentFormat format,
        Short raceTo,
        boolean handicap,
        TournamentStatus status,
        List<RoundTreeDto> rounds) {
}