package com.tgroup.fastscore.controllers;

import com.tgroup.fastscore.model.read.TournamentTreeDto;
import com.tgroup.fastscore.services.TournamentSnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TournamentSnapshotController {
    public static final String TOURNAMENT_SNAPSHOT_PATH = "/api/v1/tournament-snapshot";
    public static final String TOURNAMENT_SNAPSHOT_TOURNAMENT_PATH_ID = TOURNAMENT_SNAPSHOT_PATH + "/{tournamentId}";

    private final TournamentSnapshotService tournamentSnapshotService;

    @GetMapping(TOURNAMENT_SNAPSHOT_TOURNAMENT_PATH_ID)
    public TournamentTreeDto getTournamentById(@PathVariable("tournamentId") UUID tournamentId) {
        return this.tournamentSnapshotService.getTournamentSnapshot(tournamentId);
    }
}
