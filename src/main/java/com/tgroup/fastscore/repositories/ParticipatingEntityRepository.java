package com.tgroup.fastscore.repositories;

import com.tgroup.fastscore.entities.ParticipatingEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ParticipatingEntityRepository extends CrudRepository<ParticipatingEntity, UUID> {
    @EntityGraph(attributePaths = {"members", "members.player"})
    Set<ParticipatingEntity> findAllByTournamentId(UUID tournamentId);
}
