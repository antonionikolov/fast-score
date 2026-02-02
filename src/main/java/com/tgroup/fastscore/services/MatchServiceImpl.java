package com.tgroup.fastscore.services;

import com.tgroup.fastscore.entities.Match;
import com.tgroup.fastscore.mappers.MatchMapper;
import com.tgroup.fastscore.messaging.TournamentMatchUpdateEvent;
import com.tgroup.fastscore.model.MatchDto;
import com.tgroup.fastscore.model.MatchStatus;
import com.tgroup.fastscore.model.ScoreReportDto;
import com.tgroup.fastscore.repositories.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final ApplicationEventPublisher eventPublisher;
    private final TournamentService tournamentService;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    @Override
    public MatchDto getMatchById(UUID matchId) {
        if (matchId == null) {
            throw new RuntimeException("Required property matchId is null!");
        }

        Optional<Match> match = matchRepository.findById(matchId);
        if (match.isEmpty()) {
            throw new RuntimeException("Match with id: " + matchId + " doesn't exist!");
        }

        return this.matchMapper.matchToMatchDto(match.get());
    }

    @Override
    public List<MatchDto> getMatchesByTournamentId(UUID tournamentId) {
        tournamentService.verifyTournamentExists(tournamentId);

        return matchRepository.findAllByTournamentId(tournamentId).stream().map(matchMapper::matchToMatchDto).toList();
    }

    @Override
    public MatchDto createMatch(MatchDto matchDto) {
        return this.matchMapper.matchToMatchDto(this.matchRepository.save(this.matchMapper.matchDtoToMatch(matchDto)));
    }

    @Override
    @Transactional
    public MatchDto reportScore(UUID tournamentId, UUID matchId, ScoreReportDto scoreReportDto) {
        tournamentService.verifyTournamentExists(tournamentId);
        MatchDto match = getMatchById(matchId);

        if (match.status().equals(MatchStatus.FINISHED)) {
            throw new IllegalStateException("Cannot update scores for a finished match.");
        }

        MatchDto newMatch = match.withScore1(scoreReportDto.score1()).withScore2(scoreReportDto.score2()).withStatus(match.status());
        MatchDto savedMatch = matchMapper.matchToMatchDto(matchRepository.save(matchMapper.matchDtoToMatch(newMatch)));

        eventPublisher.publishEvent(new TournamentMatchUpdateEvent(tournamentId, matchId, Instant.now()));

        return savedMatch;
    }
}
