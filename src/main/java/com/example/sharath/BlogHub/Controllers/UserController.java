package com.example.sharath.BlogHub.Controllers;

import com.example.sharath.BlogHub.DTOs.UserDto;
import com.example.sharath.BlogHub.Exception.ResourceNotFoundException;
import com.example.sharath.BlogHub.Service.UserService.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    userServiceImpl userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user;
        try{
           user = userService.createUser(userDto);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to create User");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Long user_id){
        UserDto user;
        try{
            user = userService.updateUser(userDto,user_id);
        }
        catch (Exception e){
            throw new ResourceNotFoundException("user","id",user_id);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long user_id){
        userService.deleteUser(user_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long user_id){
        UserDto user;
        try{
            user = userService.getUserById(user_id);
        }
        catch (Exception e){
            throw new ResourceNotFoundException("user","id",user_id);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users;
        try{
            users = userService.getAllUsers();
        }
        catch (Exception e){
            throw new RuntimeException("Failed to load users");
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
