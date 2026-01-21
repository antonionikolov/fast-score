package com.tgroup.fastscore.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ParticipatingEntityDto(
        UUID id,
        UUID tournamentId,
        String name,
        Integer seed,
        boolean isActive,
        Instant createdAt,
        List<EntityMemberDto> members,
        int memberCount
) {}
