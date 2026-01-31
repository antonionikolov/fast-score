package com.tgroup.fastscore.mappers;

import com.tgroup.fastscore.entities.EntityMember;
import com.tgroup.fastscore.model.EntityMemberDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { PlayerMapper.class })
public interface EntityMemberMapper {
    EntityMemberDto entityMemberToEntityMemberDto(EntityMember participatingEntity);
    EntityMember entityMemberDtoToEntityMember(EntityMemberDto participatingEntityDto);
}
