package com.API.EquipmentRental.mapstruct;

import com.API.EquipmentRental.dto.UserDto;
import com.API.EquipmentRental.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    List<UserDto> userListToUserDtoList(List<User> users);

    List<User> userDtoListToUserList(List<UserDto> userDtos);
}
