package com.tgroup.fastscore.messaging;

import com.tgroup.fastscore.services.TournamentSnapshotServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentMatchUpdateEventConsumer {
    private final TournamentSnapshotServiceImpl tournamentSnapshotService;

    @KafkaListener(topics = EventTopics.TOURNAMENT_UPDATE)
    public void handleMatchUpdate(TournamentMatchUpdateEvent event) {
        // This is where you call the mapper and save to the JSONB table
        tournamentSnapshotService.refreshTournamentSnapshot(event.tournamentId());
    }
}