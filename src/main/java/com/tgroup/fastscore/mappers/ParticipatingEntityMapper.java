package com.tgroup.fastscore.mappers;

import com.tgroup.fastscore.entities.ParticipatingEntity;
import com.tgroup.fastscore.model.ParticipatingEntityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { EntityMemberMapper.class })
public interface ParticipatingEntityMapper {
    ParticipatingEntityDto participatingEntityToParticipatingEntityDto(ParticipatingEntity participatingEntity);
    ParticipatingEntity participatingEntityDtoToParticipatingEntity(ParticipatingEntityDto participatingEntityDto);
}
