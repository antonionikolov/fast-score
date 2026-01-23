package com.tgroup.fastscore.repositories;

import com.tgroup.fastscore.entities.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, UUID> {
}
