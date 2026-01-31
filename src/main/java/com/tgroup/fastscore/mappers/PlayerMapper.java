package com.tgroup.fastscore.mappers;

import com.tgroup.fastscore.entities.Player;
import com.tgroup.fastscore.model.PlayerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface PlayerMapper {
    PlayerDto playerToPlayerDto(Player player);
    Player playerDtoToPlayer(PlayerDto playerDto);
}