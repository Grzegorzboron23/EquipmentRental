package com.API.EquipmentRental.service;

import com.API.EquipmentRental.Exception.ResourceNotFoundException;
import com.API.EquipmentRental.dto.UserDto;
import com.API.EquipmentRental.dto.UserSlimDto;
import com.API.EquipmentRental.mapstruct.MapStructMapper;
import com.API.EquipmentRental.model.User;
import com.API.EquipmentRental.respository.UserRepository;
import com.API.EquipmentRental.utils.QueryUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${page.size}")
    private int PAGE_SIZE;
    private final UserRepository userRepository;
    private final MapStructMapper mapStructMapper;
    private static final Sort SORT_BY = Sort.by("username");

    @Override
    public List<UserDto> getAllUsers(int page) {
        List<User> userList = userRepository.findAll(createPageRequest(page)).toList();
        return getUserDtos(userList);
    }

    @Override
    public List<UserDto> getUsersByUsername(int page, String username) {
        if (username.isEmpty()) {
            throw new ResourceNotFoundException("Username cannot be blank");
        }

        String queryUsername = QueryUtils.stringToLikeQuery(username);
        List<User> userList = userRepository.
                findByUsernameLikeIgnoreCase(
                        queryUsername,
                        createPageRequest(page))
                .toList();

        return getUserDtos(userList);
    }

    @Override
    public List<UserDto> getUsersByEmail(int page, String email) {
        if (email.isEmpty()) {
            throw new ResourceNotFoundException("Email cannot be blank");
        }

        String queryEmail = QueryUtils.stringToLikeQuery(email);

        List<User> userList = userRepository.
                findByUsernameLikeIgnoreCase(
                        queryEmail,
                        createPageRequest(page))
                .toList();

        return getUserDtos(userList);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmailIgnoreCase(email);
        return getUserDto(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsernameIgnoreCase(username);
        return getUserDto(user);
    }

    @Override
    @Transactional
    public UserDto createOrUpdateUser(UserSlimDto userSlimDto) {
        User user = userRepository.save(mapStructMapper.userSlimDtoToUser(userSlimDto));
        return getUserDto(user);
    }

    @Override
    @Transactional
    public void deleteUser(String id) {
        User user = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ResourceNotFoundException("Cannot find user"));
        userRepository.delete(user);
    }

    private PageRequest createPageRequest(int page) {
        return PageRequest.of(Math.max(page, 0), PAGE_SIZE, SORT_BY);
    }

    private UserDto getUserDto(User user) {
        return mapStructMapper.userToUserDto(user);
    }

    private List<UserDto> getUserDtos(List<User> userList) {
        return mapStructMapper.userListToUserDtoList(userList);
    }
}
