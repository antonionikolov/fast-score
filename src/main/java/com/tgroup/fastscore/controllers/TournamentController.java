package com.tgroup.fastscore.controllers;

import com.tgroup.fastscore.model.TournamentDto;
import com.tgroup.fastscore.services.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/tournamets")
@RequiredArgsConstructor
public class TournamentController {
    public static final String TOURNAMENT_PATH = "/api/v1/tournaments";
    public static final String TOURNAMENT_PATH_ID = TOURNAMENT_PATH + "/{tournamentId}";

    private final TournamentService tournamentService;

    @GetMapping(TOURNAMENT_PATH_ID)
    public TournamentDto getTournamentById(@RequestParam("tournamentId") UUID tournamentId) {
        return this.tournamentService.getTournamentById(tournamentId);
    }

    @PostMapping(TOURNAMENT_PATH)
    public ResponseEntity createTournament(@RequestBody TournamentDto tournamentDto) {
        TournamentDto savedTournament = this.tournamentService.createTournament(tournamentDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", TOURNAMENT_PATH + "/" + savedTournament.id().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}

