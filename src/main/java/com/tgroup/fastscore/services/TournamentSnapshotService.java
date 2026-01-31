package com.tgroup.fastscore.services;

import com.tgroup.fastscore.model.read.TournamentTreeDto;

import java.util.UUID;

public interface TournamentSnapshotService {
    TournamentTreeDto getTournamentSnapshot(UUID tournamentId);
    TournamentTreeDto refreshTournamentSnapshot(UUID tournamentId);
}
