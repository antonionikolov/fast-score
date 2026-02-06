package com.tgroup.fastscore.services.bracket;

import com.tgroup.fastscore.model.MatchDto;
import com.tgroup.fastscore.model.MatchStatus;
import com.tgroup.fastscore.model.ParticipatingEntityDto;
import com.tgroup.fastscore.model.TournamentFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SingleEliminationGeneratorStrategy implements BracketGeneratorStrategy {

    @Override
    public TournamentFormat getSupportedFormatType() {
        return TournamentFormat.SINGLE_ELIMINATION;
    }

    @Override
    public List<MatchDto> generate(UUID tournamentId, List<ParticipatingEntityDto> participants) {
        if (participants.size() % 2 != 0) {
            throw new RuntimeException("Participants size must be even number!");
        }

        Collections.shuffle(participants);
        List<MatchDto.MatchDtoBuilder> firstRoundMatches = new ArrayList<>();
        for (int i = 0; i < participants.size(); i+= 2) {
            MatchDto.MatchDtoBuilder matchDto = MatchDto.builder()
                    .id(UUID.randomUUID())
                    .tournamentId(tournamentId)
                    .participant1Id(participants.get(i).id())
                    .participant2Id(participants.get(i + 1).id())
                    .participant1Name(participants.get(i).name())
                    .participant2Name(participants.get(i + 1).name())
                    .status(MatchStatus.READY)
                    .roundNumber((short) 1)
                    .sortOrder((short) (i / 2))
                    .nextMatchSlot(((i / 2) % 2) + 1);

            firstRoundMatches.add(matchDto);
        }

        List<MatchDto.MatchDtoBuilder> allBuilders =
                this.generateRestOfTheBracket(tournamentId, 2, firstRoundMatches.size(), firstRoundMatches);

        return allBuilders.stream().map(MatchDto.MatchDtoBuilder::build).toList();
    }

    private List<MatchDto.MatchDtoBuilder> generateRestOfTheBracket(UUID tournamentId,
                                                                    int roundNumber,
                                                                    int sortOrderStart,
                                                                    List<MatchDto.MatchDtoBuilder> matchDtoBuilders) {
        if (matchDtoBuilders.size() == 1) {
            return matchDtoBuilders;
        }

        List<MatchDto.MatchDtoBuilder> newMatchDtoBuilders = new ArrayList<>();
        for (int i = 0; i < matchDtoBuilders.size(); i += 2) {
            UUID currentMatchId = UUID.randomUUID();
            MatchDto.MatchDtoBuilder newMatchDtoBuilder = MatchDto.builder()
                    .id(UUID.randomUUID())
                    .tournamentId(tournamentId)
                    .roundNumber((short) roundNumber)
                    .sortOrder((short) (sortOrderStart + i/2))
                    .status(MatchStatus.WAITING)
                    .nextMatchSlot(((i / 2) % 2) + 1);

            matchDtoBuilders.get(i).nextMatchId(currentMatchId);
            matchDtoBuilders.get(i + 1).nextMatchId(currentMatchId);
            newMatchDtoBuilders.add(newMatchDtoBuilder);
        }

        matchDtoBuilders.addAll(this.generateRestOfTheBracket(tournamentId,
                roundNumber + 1,
                sortOrderStart + newMatchDtoBuilders.size(),
                newMatchDtoBuilders));

        return matchDtoBuilders;
    }
}
