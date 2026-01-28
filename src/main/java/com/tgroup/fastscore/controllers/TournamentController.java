package com.tgroup.fastscore.controllers;

import com.tgroup.fastscore.model.TournamentDto;
import com.tgroup.fastscore.services.BracketService;
import com.tgroup.fastscore.services.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/tournamets")
@RequiredArgsConstructor
public class TournamentController {
    public static final String TOURNAMENT_PATH = "/api/v1/tournaments";
    public static final String TOURNAMENT_PATH_ID = TOURNAMENT_PATH + "/{tournamentId}";
    public static final String TOURNAMENT_PATH_GENERATE = TOURNAMENT_PATH_ID + "/initial-generate";

    private final TournamentService tournamentService;
    private final BracketService bracketService;

    @GetMapping(TOURNAMENT_PATH_ID)
    public TournamentDto getTournamentById(@RequestParam("tournamentId") UUID tournamentId) {
        return this.tournamentService.getTournamentById(tournamentId);
    }

    @PostMapping(TOURNAMENT_PATH)
    public ResponseEntity createTournament(@Validated @RequestBody TournamentDto tournamentDto) {
        TournamentDto savedTournament = this.tournamentService.createTournament(tournamentDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", TOURNAMENT_PATH + "/" + savedTournament.id().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PostMapping(TOURNAMENT_PATH_GENERATE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void generateInitialBracket(@RequestParam("tournamentId") UUID tournamentId) {
        this.bracketService.generateInitialRound(tournamentId);
    }
}

