package com.API.EquipmentRental.service;


import com.API.EquipmentRental.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers(int page);

    List<UserDto> getUsersByUsername(int page, String username);

    List<UserDto> getUsersByEmail(int page, String email);

}
