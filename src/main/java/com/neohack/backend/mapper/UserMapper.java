package com.neohack.backend.mapper;

import com.neohack.backend.dto.RegistrationDto;
import com.neohack.backend.dto.UserDto;
import com.neohack.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser (UserDto userDto);
    UserDto toUserDto (User user);

    @Mapping(target="username", source = "registrationDto.username")
    User toUser (RegistrationDto registrationDto);
    RegistrationDto toUser (User user);
}
