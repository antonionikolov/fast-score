package com.tgroup.fastscore.repositories;

import com.tgroup.fastscore.entities.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepository extends CrudRepository<Match, UUID> {
    List<Match> findAllByTournamentId(UUID tournamentId);
}
