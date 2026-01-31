package com.tgroup.fastscore.services;

import com.tgroup.fastscore.model.MatchDto;
import com.tgroup.fastscore.model.ScoreReportDto;

import java.util.List;
import java.util.UUID;

public interface MatchService {
    MatchDto getMatchById(UUID matchId);
    List<MatchDto> getMatchesByTournamentId(UUID tournamentId);
    MatchDto createMatch(MatchDto matchDto);
    MatchDto reportScore(UUID tournamentId, UUID matchId, ScoreReportDto scoreReportDto);
}
