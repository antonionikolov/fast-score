package com.tgroup.fastscore.model.read;

import com.tgroup.fastscore.model.ParticipatingEntityStatus;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ParticipantNodeDto(
        UUID participantId,
        String participantName,
        ParticipatingEntityStatus status,
        List<ParticipantNodeMember> members
) {
}
