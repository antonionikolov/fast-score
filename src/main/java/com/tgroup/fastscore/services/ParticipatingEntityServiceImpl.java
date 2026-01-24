package com.tgroup.fastscore.services;

import com.tgroup.fastscore.entities.ParticipatingEntity;
import com.tgroup.fastscore.entities.Tournament;
import com.tgroup.fastscore.mappers.ParticipatingEntityMapper;
import com.tgroup.fastscore.model.ParticipatingEntityDto;
import com.tgroup.fastscore.repositories.ParticipatingEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipatingEntityServiceImpl implements ParticipatingEntityService {
    private final ParticipatingEntityRepository participatingEntityRepository;
    private final ParticipatingEntityMapper participatingEntityMapper;

    public ParticipatingEntityDto getParticipatingEntityById(UUID participatingEntityId) {
        if (participatingEntityId == null) {
            throw new RuntimeException("Required property participatingEntityId is null!");
        }

        Optional<ParticipatingEntity> participatingEntity =
                participatingEntityRepository.findById(participatingEntityId);
        if (participatingEntity.isEmpty()) {
            throw new RuntimeException("Participating entity with id: " + participatingEntityId + " doesn't exist!");
        }

        return this.participatingEntityMapper.participatingEntityToParticipatingEntityDto(participatingEntity.get());
    }

    public ParticipatingEntityDto addParticipatingEntity(
            UUID tournamentId,
            ParticipatingEntityDto participatingEntityDto
    ) {
        return this.participatingEntityMapper.participatingEntityToParticipatingEntityDto(
                this.participatingEntityRepository.save(
                        this.participatingEntityMapper.participatingEntityDtoToParticipatingEntity(
                                participatingEntityDto)));
    }
}
