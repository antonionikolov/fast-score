package com.tgroup.fastscore.services;

import com.tgroup.fastscore.entities.Match;
import com.tgroup.fastscore.mappers.MatchMapper;
import com.tgroup.fastscore.model.MatchDto;
import com.tgroup.fastscore.model.MatchStatus;
import com.tgroup.fastscore.model.ParticipatingEntityDto;
import com.tgroup.fastscore.repositories.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BracketServiceImpl implements BracketService {
    private final TournamentService tournamentService;
    private final ParticipatingEntityService participatingEntityService;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    @Override
    @Transactional
    public void generateInitialRound(UUID tournamentId) {
        tournamentService.verifyTournamentExists(tournamentId);

        List<ParticipatingEntityDto> participants =
                participatingEntityService.getParticipatingEntitiesByTournamentId(tournamentId);

        if (participants.size() % 2 != 0) {
           throw new RuntimeException("Participants size must be even number!");
        }

        Collections.shuffle(participants);
        List<MatchDto.MatchDtoBuilder> firstRoundMatches = new ArrayList<>();
        for (int i = 0; i < participants.size(); i+= 2) {
            MatchDto.MatchDtoBuilder matchDto = MatchDto.builder()
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

        List<MatchDto.MatchDtoBuilder> allBuilders = this.generateRestOfTheBracket(tournamentId, 2, firstRoundMatches.size(), firstRoundMatches);
        List<Match> allMatches = allBuilders.stream().map((matchBuilder) -> matchMapper.matchDtoToMatch(matchBuilder.build())).toList();
        matchRepository.saveAll(allMatches);
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
            MatchDto.MatchDtoBuilder newMatchDtoBuilder = MatchDto.builder()
                    .tournamentId(tournamentId)
                    .roundNumber((short) roundNumber)
                    .sortOrder((short) (sortOrderStart + i/2))
                    .status(MatchStatus.WAITING)
                    .nextMatchSlot(((i / 2) % 2) + 1);
            Match match = matchRepository.save(matchMapper.matchDtoToMatch(newMatchDtoBuilder.build()));
            newMatchDtoBuilder.id(match.getId());

            matchDtoBuilders.get(i).nextMatchId(match.getId());
            matchDtoBuilders.get(i + 1).nextMatchId(match.getId());
            newMatchDtoBuilders.add(newMatchDtoBuilder);
        }

        matchDtoBuilders.addAll(this.generateRestOfTheBracket(tournamentId,
                roundNumber + 1,
                sortOrderStart + newMatchDtoBuilders.size(),
                newMatchDtoBuilders));

        return matchDtoBuilders;
    }
}
