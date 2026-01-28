package com.tgroup.fastscore.services;

import com.tgroup.fastscore.model.ParticipatingEntityDto;

import java.util.List;
import java.util.UUID;

public interface ParticipatingEntityService {
    ParticipatingEntityDto getParticipatingEntityById(UUID participatingEntityId);
    List<ParticipatingEntityDto> getParticipatingEntitiesByTournamentId(UUID tournamentId);
    ParticipatingEntityDto addParticipatingEntity(UUID tournamentId, ParticipatingEntityDto participatingEntityDto);
}
