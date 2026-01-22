package com.tgroup.fastscore.controllers;

import com.tgroup.fastscore.model.ParticipatingEntityDto;
import com.tgroup.fastscore.services.ParticipatingEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ParticipatingEntityController {
    public static final String PARTICIPATING_ENTITY_PATH = "/participating-entity";
    public static final String PARTICIPATING_ENTITY_PATH_ID = TournamentController.TOURNAMENT_PATH_ID + PARTICIPATING_ENTITY_PATH + "/{participatingEntityId}";

    private final ParticipatingEntityService participatingEntityService;

    @GetMapping(PARTICIPATING_ENTITY_PATH_ID)
    public ParticipatingEntityDto getParticipatingEntityById(
            @RequestParam("tournamentId") UUID tournamentId,
            @RequestParam("participatingEntityId") UUID participatingEntityId
    ) {
        return this.participatingEntityService.getParticipatingEntityById(tournamentId, participatingEntityId);
    }

    @PostMapping(PARTICIPATING_ENTITY_PATH)
    public ResponseEntity addParticipatingEntity(
            @RequestParam("tournamentId") UUID tournamentId,
            @RequestBody ParticipatingEntityDto participatingEntityDto
    ) {
        ParticipatingEntityDto savedParticipatingEntity =
                this.participatingEntityService.addParticipatingEntity(tournamentId, participatingEntityDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location",
                TournamentController.TOURNAMENT_PATH + "/" + participatingEntityDto.tournamentId().toString()
                + PARTICIPATING_ENTITY_PATH + "/" + participatingEntityDto.id().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
