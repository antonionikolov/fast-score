package com.tgroup.fastscore.services;

import com.tgroup.fastscore.model.MatchDto;
import java.util.UUID;

public interface MatchService {
    MatchDto getMatchById(UUID matchId);
    MatchDto createMatch(MatchDto matchDto);
}
