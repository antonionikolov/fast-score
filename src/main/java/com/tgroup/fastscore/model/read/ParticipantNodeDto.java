package com.tgroup.fastscore.model.read;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ParticipantNodeDto(
        UUID participantId,
        String participantName,
        String status,
        List<ParticipantNodeMember> members
) {
}
