package com.tgroup.fastscore.mappers;

import com.tgroup.fastscore.entities.Tournament;
import com.tgroup.fastscore.model.TournamentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TournamentMapper {
    TournamentDto tournamentToTournamentDto(Tournament tournament);
    Tournament tournamentDtoToTournament(TournamentDto unitDto);
}