package com.tgroup.fastscore.model;

import java.util.UUID;

public record EntityMemberDto(
        UUID playerId,
        String playerName,
        String rankAtRegistration
) {}