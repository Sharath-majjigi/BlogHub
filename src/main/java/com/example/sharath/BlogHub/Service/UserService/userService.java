package com.example.sharath.BlogHub.Service.UserService;

import com.example.sharath.BlogHub.DTOs.UserDto;

import java.util.List;

public interface userService {

    UserDto createUser(UserDto userDto);

    UserDto registerUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long user_id);

    void deleteUser(Long user_id);

    UserDto getUserById(Long user_id);

    List<UserDto> getAllUsers();
}
