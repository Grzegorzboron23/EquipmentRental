package com.API.EquipmentRental.controller;

import com.API.EquipmentRental.dto.UserDto;
import com.API.EquipmentRental.dto.UserSlimDto;
import com.API.EquipmentRental.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
            @RequestParam @NotBlank(message = "Email cannot be blank") String email) {
        return ResponseEntity.ok(userService.getUsersByEmail(page - 1, email));
    }


    @GetMapping("/users/findByUsername/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/users/findByEmail/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByUsername(email));
    }


    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserSlimDto userSlimDto) {
        return ResponseEntity.ok(userService.createOrUpdateUser(userSlimDto));
    }


    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserSlimDto userSlimDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createOrUpdateUser(userSlimDto));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
