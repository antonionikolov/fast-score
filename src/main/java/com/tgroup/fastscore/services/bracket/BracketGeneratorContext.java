package com.tgroup.fastscore.services.bracket;

import com.tgroup.fastscore.model.MatchDto;
import com.tgroup.fastscore.model.ParticipatingEntityDto;
import com.tgroup.fastscore.model.TournamentDto;
import com.tgroup.fastscore.model.TournamentFormat;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BracketGeneratorContext {
    private final Map<TournamentFormat, BracketGeneratorStrategy> strategies;

    public BracketGeneratorContext(List<BracketGeneratorStrategy> strategyList) {
        this.strategies = strategyList.stream().collect(
                Collectors.toMap(BracketGeneratorStrategy::getSupportedFormatType, strategy -> strategy)
        );
    }

    // TODO: start using the strategy from the bracketserviceimpl
    public List<MatchDto> generate(TournamentDto tournamentDto, List<ParticipatingEntityDto> participants) {
        return Optional.ofNullable(strategies.get(tournamentDto.format()))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported type: " + tournamentDto.format()))
                .generate(tournamentDto.id(), participants);
    }
}
