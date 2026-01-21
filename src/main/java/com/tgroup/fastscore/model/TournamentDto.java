package com.tgroup.fastscore.model;

import java.time.Instant;
import java.util.UUID;

public record TournamentDto(
        UUID id,
        String name,
        Instant startsAt,
        String venueNameManual,
        String organiserType,
        String format,
        Short raceTo,
        String status
) {}
