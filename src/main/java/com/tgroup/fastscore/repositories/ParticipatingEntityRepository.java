package com.tgroup.fastscore.repositories;

import com.tgroup.fastscore.entities.ParticipatingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipatingEntityRepository extends CrudRepository<ParticipatingEntity, UUID> {
}
