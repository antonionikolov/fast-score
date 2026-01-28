package com.tgroup.fastscore.services;

import com.tgroup.fastscore.entities.Match;
import com.tgroup.fastscore.mappers.MatchMapper;
import com.tgroup.fastscore.model.MatchDto;
import com.tgroup.fastscore.repositories.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService{
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
    public MatchDto createMatch(MatchDto matchDto) {
        return this.matchMapper.matchToMatchDto(this.matchRepository.save(this.matchMapper.matchDtoToMatch(matchDto)));
    }
}
