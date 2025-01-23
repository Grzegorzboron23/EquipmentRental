package com.API.EquipmentRental.controller;

import com.API.EquipmentRental.dto.UserDto;
import com.API.EquipmentRental.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(required = false, defaultValue = "1") int page) {
        return ResponseEntity.ok(userService.getAllUsers(page - 1));
    }

    @GetMapping("/users/username")
    public ResponseEntity<List<UserDto>> getAllUsersByUsername(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam String username) {
        return ResponseEntity.ok(userService.getUsersByUsername(page - 1, username));
    }

    @GetMapping("/users/email")
    public ResponseEntity<List<UserDto>> getAllUsersByEmail(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam String email) {
        return ResponseEntity.ok(userService.getUsersByEmail(page - 1, email));
    }
}
