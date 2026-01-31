package com.tgroup.fastscore.model;

import java.util.UUID;

public record EntityMemberDto(
        PlayerDto player,
        UUID participatingEntityId,
        String rankAtRegistration
) {}