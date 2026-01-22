package com.tgroup.fastscore.services;

import com.tgroup.fastscore.model.ParticipatingEntityDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParticipatingEntityServiceImpl implements ParticipatingEntityService {
    public ParticipatingEntityDto getParticipatingEntityById(UUID tournamentId, UUID participatingEntityId) {
        return null;
    }

    public ParticipatingEntityDto addParticipatingEntity(
            UUID tournamentId,
            ParticipatingEntityDto participatingEntityDto
    ) {
        return null;
    }
}
