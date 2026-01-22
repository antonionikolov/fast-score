package com.tgroup.fastscore.services;

import com.tgroup.fastscore.model.TournamentDto;

import java.util.UUID;

public interface TournamentService {
    TournamentDto getTournamentById(UUID tournamentId);
    TournamentDto createTournament(TournamentDto tournamentDto);
}
