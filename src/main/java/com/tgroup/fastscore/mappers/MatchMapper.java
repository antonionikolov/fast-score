package com.tgroup.fastscore.mappers;

import com.tgroup.fastscore.entities.Match;
import com.tgroup.fastscore.model.MatchDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    MatchDto matchToMatchDto(Match match);
    Match matchDtoToMatch(MatchDto unitDto);
}
