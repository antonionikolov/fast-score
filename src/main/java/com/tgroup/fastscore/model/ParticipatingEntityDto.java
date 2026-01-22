package com.tgroup.fastscore.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ParticipatingEntityDto(
        UUID id,
        UUID tournamentId,
        String name,
        Integer seed,
        /*
            WAITING: Registered but on the waiting list (tournament is full).
            CONFIRMED: Registered and ready to play.
            ACTIVE: Currently has an ongoing match.
            ELIMINATED: Lost in a knockout bracket.
            WITHDRAWN: Voluntarily left before or during the event.
            DISQUALIFIED: Removed by an official (crucial for integrity).
         */
        String status,
        Instant createdAt,
        List<EntityMemberDto> members,
        int memberCount
) {}
