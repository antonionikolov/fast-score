package com.tgroup.fastscore.mappers;

import com.tgroup.fastscore.entities.User;
import com.tgroup.fastscore.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User player);
    User userDtoToUser(UserDto playerDto);
}
