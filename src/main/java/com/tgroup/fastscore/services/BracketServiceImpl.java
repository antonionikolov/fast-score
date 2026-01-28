package com.tgroup.fastscore.services;

import com.tgroup.fastscore.entities.Match;
import com.tgroup.fastscore.mappers.MatchMapper;
import com.tgroup.fastscore.model.MatchDto;
import com.tgroup.fastscore.model.ParticipatingEntityDto;
import com.tgroup.fastscore.model.TournamentDto;
import com.tgroup.fastscore.repositories.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BracketServiceImpl {
    private final TournamentService tournamentService;
    private final ParticipatingEntityService participatingEntityService;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    @Transactional
    void generateInitialRound(UUID tournamentId) {
        TournamentDto tournament = tournamentService.getTournamentById(tournamentId);

        List<ParticipatingEntityDto> participants =
                participatingEntityService.getParticipatingEntitiesByTournamentId(tournamentId);

        if (participants.size() % 2 != 0) {
           throw new RuntimeException("Participants size must be even number!");
        }

        Collections.shuffle(participants);
        List<MatchDto.MatchDtoBuilder> firstRoundMatches = new ArrayList<>();
        for (int i = 0; i < participants.size(); i+= 2) {
            MatchDto.MatchDtoBuilder matchDto = MatchDto.builder()
                    .tournamentId(tournament.id())
                    .participant1Id(participants.get(i))
                    .participant2Id(participants.get(i + 1))
                    .participant1Name(participants.get(i).name())
                    .participant2Name(participants.get(i + 1).name())
                    .roundNumber(1)
                    .sortOrder(i)
                    .nextMatchSlot(((i / 2) % 2) + 1);

            firstRoundMatches.add(matchDto);
        }

//        matchRepository.saveAll(firstRoundMatches.stream().map(matchMapper::matchDtoToMatch).collect(Collectors.toList()));

    }

    private List<MatchDto> generateRestOfTheBracket(UUID tournamentId, int roundNumber, int sortOrderStart, List<MatchDto> matches) {
        if (matches.isEmpty()) {
            return new ArrayList<>();
        }

        for (int i = 0; i < matches.size(); i += 2) {
            MatchDto.MatchDtoBuilder matchDto = MatchDto.builder()
                    .tournamentId(tournamentId)
                    .roundNumber(roundNumber)
                    .sortOrder(sortOrderStart + i)
                    .nextMatchSlot(((i / 2) % 2) + 1);
//            Match match = matchRepository.save(matchMapper.matchDtoToMatch(matchDto));
//            matches.get(i).
        }
    }
}
