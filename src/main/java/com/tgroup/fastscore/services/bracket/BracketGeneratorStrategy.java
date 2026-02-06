package com.tgroup.fastscore.services.bracket;

import com.tgroup.fastscore.model.MatchDto;
import com.tgroup.fastscore.model.ParticipatingEntityDto;
import com.tgroup.fastscore.model.TournamentFormat;

import java.util.List;
import java.util.UUID;

public interface BracketGeneratorStrategy {
    TournamentFormat getSupportedFormatType();
    List<MatchDto> generate(UUID tournamentId, List<ParticipatingEntityDto> participants);
}
