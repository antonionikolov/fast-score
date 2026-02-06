package com.tgroup.fastscore.services.bracket;

import com.tgroup.fastscore.model.MatchDto;
import com.tgroup.fastscore.model.ParticipatingEntityDto;
import com.tgroup.fastscore.model.TournamentFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DoubleEliminationGeneratorStrategy implements BracketGeneratorStrategy {

    @Override
    public TournamentFormat getSupportedFormatType() {
        return TournamentFormat.DOUBLE_ELIMINATION;
    }

    @Override
    public List<MatchDto> generate(UUID tournamentId, List<ParticipatingEntityDto> participants) {
        //TODO: implement
        return null;
    }
}
