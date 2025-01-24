package com.API.EquipmentRental.config;

import com.API.EquipmentRental.Exception.ResourceNotFoundException;
import com.API.EquipmentRental.dto.UserDto;
import com.API.EquipmentRental.enums.Role;
import com.API.EquipmentRental.mapstruct.MapStructMapper;
import com.API.EquipmentRental.model.User;
import com.API.EquipmentRental.respository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MapStructMapper mapStructMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with name" + username + " not found")
                );

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getHashedPassword())
                .roles(user.getRole().name())
                .build();
    }

    @Transactional
    public UserDto registerUser(String username,String email,String password){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(Role.USER);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);

        return mapStructMapper.userToUserDto(user);
    }
}
