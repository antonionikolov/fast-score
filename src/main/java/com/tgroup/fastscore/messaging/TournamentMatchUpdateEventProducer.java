package com.tgroup.fastscore.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TournamentMatchUpdateEventProducer {
    private final KafkaTemplate<String, TournamentMatchUpdateEvent> kafkaTemplate;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTournamentMatchUpdate(TournamentMatchUpdateEvent tournamentMatchUpdateEvent) {
        kafkaTemplate.send(EventTopics.TOURNAMENT_UPDATE, tournamentMatchUpdateEvent.tournamentId().toString(),
                new TournamentMatchUpdateEvent(
                        tournamentMatchUpdateEvent.tournamentId(),
                        tournamentMatchUpdateEvent.matchId(),
                        Instant.now()
                )
        );
    }
}