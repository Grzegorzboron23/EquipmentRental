package com.API.EquipmentRental.mapstruct;

import com.API.EquipmentRental.dto.EquipmentDto;
import com.API.EquipmentRental.dto.EquipmentSlimDto;
import com.API.EquipmentRental.dto.UserDto;
import com.API.EquipmentRental.dto.UserSlimDto;
import com.API.EquipmentRental.model.Equipment;
import com.API.EquipmentRental.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    List<UserDto> userListToUserDtoList(List<User> users);

    List<User> userDtoListToUserList(List<UserDto> userDtos);

    EquipmentDto equipmentToEquipmentDto(Equipment equipment);

    Equipment equipmentDtoToEquipment(EquipmentDto equipmentDto);

    List<EquipmentDto> equipmentListToEquipmentDtoList(List<Equipment> equipments);

    List<Equipment> equipmentDtoListToEquipmentList(List<EquipmentDto> equipmentDtos);

    Equipment equipmentSlimDtoToEquipment(EquipmentSlimDto equipmentSlimDto);

    User userSlimDtoToUser(UserSlimDto userSlimDto);
}
