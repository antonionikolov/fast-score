package com.tgroup.fastscore.repositories;

import com.tgroup.fastscore.entities.read.TournamentCurrentSnapshot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TournamentCurrentSnapshotRepository extends CrudRepository<TournamentCurrentSnapshot, UUID> {
}
