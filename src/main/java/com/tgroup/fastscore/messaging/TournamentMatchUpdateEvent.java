package com.tgroup.fastscore.messaging;

import java.time.Instant;
import java.util.UUID;

public record TournamentMatchUpdateEvent(UUID tournamentId, UUID matchId, Instant timestamp) {
}