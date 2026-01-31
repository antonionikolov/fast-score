package com.tgroup.fastscore.model.read;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ParticipantNodeMember(UUID playerId, String name, String rank) {}
