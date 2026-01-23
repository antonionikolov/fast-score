package com.tgroup.fastscore.services;

import com.tgroup.fastscore.entities.Tournament;
import com.tgroup.fastscore.mappers.TournamentMapper;
import com.tgroup.fastscore.model.TournamentDto;
import com.tgroup.fastscore.repositories.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    @Override
    public TournamentDto getTournamentById(UUID tournamentId) {
        if (tournamentId == null) {
            throw new RuntimeException("Required property tournament id is null!");
        }

        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        if (tournament.isEmpty()) {
            throw new RuntimeException("Tournament with id: " + tournamentId + " doesn't exist!");
        }

        return this.tournamentMapper.tournamentToTournamentDto(tournament.get());
    }

    @Override
    public TournamentDto createTournament(TournamentDto tournamentDto) {
        return this.tournamentMapper.tournamentToTournamentDto(
                this.tournamentRepository.save(
                        this.tournamentMapper.tournamentDtoToTournament(tournamentDto)
                )
        );
    }
}
