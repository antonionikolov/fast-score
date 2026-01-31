package com.tgroup.fastscore.controllers;

import com.tgroup.fastscore.model.MatchDto;
import com.tgroup.fastscore.model.ScoreReportDto;
import com.tgroup.fastscore.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MatchController {
    public static final String MATCH_REPORT_SCORE_PATH_ID = TournamentController.TOURNAMENT_PATH_ID + "/{matchId}/report-score";

    private final MatchService matchService;

    @PostMapping(MATCH_REPORT_SCORE_PATH_ID)
    public ResponseEntity<MatchDto> reportScore(@PathVariable("tournamentId") UUID tournamentId,
                                                @PathVariable("matchId") UUID matchId,
                                                @Validated @RequestBody ScoreReportDto scoreReportDto
    ) {
        return ResponseEntity.ok(matchService.reportScore(tournamentId, matchId, scoreReportDto));
    }
}
