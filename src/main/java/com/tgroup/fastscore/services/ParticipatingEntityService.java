package com.tgroup.fastscore.services;

import com.tgroup.fastscore.model.ParticipatingEntityDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface ParticipatingEntityService {
    ParticipatingEntityDto getParticipatingEntityById(UUID tournamentId, UUID participatingEntityId);
    ParticipatingEntityDto addParticipatingEntity(UUID tournamentId, ParticipatingEntityDto participatingEntityDto);
}
