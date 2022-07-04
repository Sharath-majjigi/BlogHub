package com.example.sharath.BlogHub.Service.UserService;

import com.example.sharath.BlogHub.Constants.AppConstants;
import com.example.sharath.BlogHub.DTOs.UserDto;
import com.example.sharath.BlogHub.Exception.ResourceNotFoundException;
import com.example.sharath.BlogHub.Models.Role;
import com.example.sharath.BlogHub.Models.User;
import com.example.sharath.BlogHub.Repository.RoleDAO;
import com.example.sharath.BlogHub.Repository.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class userServiceImpl implements userService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleDAO roleDAO;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser= userDAO.save(user);
        Role role=roleDAO.findById(AppConstants.NORMAL_USER).get();
        savedUser.getRoles().add(role);
        return userToDto(savedUser);

    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user=modelMapper.map(userDto,User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role=roleDAO.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User newUser=userDAO.save(user);
        UserDto mappedDto= modelMapper.map(newUser,UserDto.class);
        return mappedDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long user_id) {
         User user = userDAO.findById(user_id).get();
       if(userDto.getEmail()!=null) user.setEmail(userDto.getEmail());
       user.setName(userDto.getName());
       user.setPassword(passwordEncoder.encode(userDto.getPassword()));
       user.setAbout(userDto.getAbout());
       User updatedUser = userDAO.save(user);
        return userToDto(updatedUser);
    }

    @Override
    public void deleteUser(Long user_id) {
        User user = userDAO.findById(user_id).get();
        if(user!=null){
            userDAO.delete(user);
        }else{
            throw new ResourceNotFoundException("User","UserId",user_id);
        }
    }

    @Override
    public UserDto getUserById(Long user_id) {
        User user = userDAO.findById(user_id).get();
        return userToDto(user);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDAO.findAll();
        //catch each user and convert them to DTOs
        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    public User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
//        user.setId(userDto.getUserid());
//        user.setEmail(userDto.getEmail());
//        user.setName(userDto.getName());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
//        userDto.setUserid(user.getId());
//        userDto.setEmail(user.getEmail());
//        userDto.setName(user.getName());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
