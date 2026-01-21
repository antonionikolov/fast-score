package com.tgroup.fastscore.model;

import java.util.UUID;

public record PlayerDto(
        UUID id,
        String displayName,
        UUID userId,
        boolean isClaimed
) {}
