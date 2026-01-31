package com.tgroup.fastscore.services;

import com.tgroup.fastscore.entities.read.TournamentCurrentSnapshot;
import com.tgroup.fastscore.mappers.read.TournamentTreeMapper;
import com.tgroup.fastscore.model.read.TournamentTreeDto;
import com.tgroup.fastscore.repositories.TournamentCurrentSnapshotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TournamentSnapshotServiceImpl implements TournamentSnapshotService {
    private final TournamentService tournamentService;
    private final ParticipatingEntityService participatingEntityService;
    private final MatchService matchService;
    private final TournamentCurrentSnapshotRepository tournamentCurrentSnapshotRepository;
    private final TournamentTreeMapper tournamentTreeMapper;

    @Override
    @Transactional
    public TournamentTreeDto getTournamentSnapshot(UUID tournamentId) {
        Optional<TournamentCurrentSnapshot> tournamentCurrentSnapshot = tournamentCurrentSnapshotRepository.findById(tournamentId);

        if (tournamentCurrentSnapshot.isPresent()) {
            return tournamentCurrentSnapshot.get().getTournamentData();
        }

        tournamentService.verifyTournamentExists(tournamentId);

        return this.refreshTournamentSnapshot(tournamentId);
    }

    @Override
    @Transactional
    public TournamentTreeDto refreshTournamentSnapshot(UUID tournamentId) {
        TournamentTreeDto tournamentTreeDto = tournamentTreeMapper.tournamentToTournamentTree(
                tournamentService.getTournamentById(tournamentId),
                matchService.getMatchesByTournamentId(tournamentId),
                participatingEntityService.getParticipatingEntitiesByTournamentId(tournamentId)
        );

        tournamentCurrentSnapshotRepository.save(new TournamentCurrentSnapshot(tournamentId, tournamentTreeDto, Instant.now()));

        return tournamentTreeDto;
    }
}
