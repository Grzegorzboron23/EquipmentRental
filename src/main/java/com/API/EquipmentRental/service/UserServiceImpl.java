package com.API.EquipmentRental.service;

import com.API.EquipmentRental.Exception.UserNotFoundException;
import com.API.EquipmentRental.dto.UserDto;
import com.API.EquipmentRental.mapstruct.MapStructMapper;
import com.API.EquipmentRental.model.User;
import com.API.EquipmentRental.respository.UserRepository;
import com.API.EquipmentRental.utils.QueryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapStructMapper mapStructMapper;
    private static final int PAGE_SIZE = 10;
    private static final Sort SORT_BY = Sort.by("username");


    @Override
    public List<UserDto> getAllUsers(int page) {
        List<User> userList = userRepository.findAll(createPageRequest(page)).toList();

        return mapStructMapper.userListToUserDtoList(userList);
    }

    @Override
    public List<UserDto> getUsersByUsername(int page, String username) {
        if (username.isEmpty()) {
            throw new UserNotFoundException("Username cannot be blank");
        }

        String queryUsername = QueryUtils.stringToLikeQuery(username);
        List<User> userList = userRepository.
                findByUsernameLikeIgnoreCase(
                        queryUsername,
                        createPageRequest(page))
                .toList();

        return mapStructMapper.userListToUserDtoList(userList);
    }

    @Override
    public List<UserDto> getUsersByEmail(int page, String email) {
        if (email.isEmpty()) {
            throw new UserNotFoundException("Email cannot be blank");
        }

        String queryEmail = QueryUtils.stringToLikeQuery(email);

        List<User> userList = userRepository.
                findByUsernameLikeIgnoreCase(
                        queryEmail,
                        createPageRequest(page))
                .toList();

        return mapStructMapper.userListToUserDtoList(userList);
    }

    private PageRequest createPageRequest(int page) {
        return PageRequest.of(Math.max(page, 0), PAGE_SIZE, SORT_BY);
    }
}
