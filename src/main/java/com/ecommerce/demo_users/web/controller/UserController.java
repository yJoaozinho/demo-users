package com.ecommerce.demo_users.web.controller;

import com.ecommerce.demo_users.entity.User;
import com.ecommerce.demo_users.service.UserService;
import com.ecommerce.demo_users.web.controller.dto.*;
import com.ecommerce.demo_users.web.controller.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto){
        User user = userService.save(UserMapper.toUser(userCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id){
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(user));
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<User> users = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toListDto(users));
    }
    @PatchMapping("/{id}/password")
    public ResponseEntity<UserResponseDto> updatePassword(@PathVariable Long id, @Valid @RequestBody UserChangePasswordDto u){
        User user = userService.updatePassword(id, u.getPassword(), u.getNewPassword(), u.getConfirmedPassword());
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(user));
    }
    @PatchMapping("/{id}/username")
    public ResponseEntity<UserResponseDto> updateUsername( @PathVariable Long id, @Valid @RequestBody UserChangeUsernameDto u){
        User user = userService.updateUsername(id, u.getUsername(), u.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(user));
    }
    @PatchMapping("/{id}/email")
    public ResponseEntity<UserResponseDto> updateEmail( @PathVariable Long id, @Valid @RequestBody UserChangeEmailDto u){
        User user = userService.updateEmail(id, u.getPassword(), u.getNewEmail(), u.getConfirmedEmail());
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(user));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteUser(@PathVariable Long id){
        userService.delete(id);
    }

}
