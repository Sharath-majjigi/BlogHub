package com.example.sharath.BlogHub.Controllers;

import com.example.sharath.BlogHub.DTOs.JwtAuthRequest;
import com.example.sharath.BlogHub.DTOs.JwtAuthResponse;
import com.example.sharath.BlogHub.DTOs.UserDto;
import com.example.sharath.BlogHub.Exception.JwtIllegalArgsException;
import com.example.sharath.BlogHub.Exception.ResourceNotFoundException;
import com.example.sharath.BlogHub.Security.CustomUserDetailService;
import com.example.sharath.BlogHub.Security.JwtTokenHelper;
import com.example.sharath.BlogHub.Service.UserService.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    CustomUserDetailService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    userServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
        authenticate(request.getUserName(),request.getPassword());
        UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUserName());
        String token=jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse authResponse=new JwtAuthResponse();
        authResponse.setJwtToken(token);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private void authenticate(String userName, String password) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userName,password);
        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (Exception e){
            throw new JwtIllegalArgsException("Username or Password is not valid");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto registeredUser;
        try{
            registeredUser=userService.registerUser(userDto);
        }catch (Exception e){
            throw new RuntimeException("Unable to register user");
        }
        return new ResponseEntity<>(registeredUser,HttpStatus.ACCEPTED);
    }


}
