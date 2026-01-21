package com.tgroup.fastscore.model;

import java.util.UUID;

public record TournamentContactDto(
        UUID tournamentId,
        UUID userId,
        String role
) {}
