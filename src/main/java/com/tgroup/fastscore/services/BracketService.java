package com.tgroup.fastscore.services;

import java.util.UUID;

public interface BracketService {
    void generateInitialRound(UUID tournamentId);
}
