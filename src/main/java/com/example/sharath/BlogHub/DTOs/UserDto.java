package com.example.sharath.BlogHub.DTOs;

import com.example.sharath.BlogHub.Models.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    public Long id;

    @Email
    public String email;

    @NotNull
    public String name;

    @NotNull
    @Size(min = 3,message = "Password must be minimum 6 characters and maximum of 10",max = 10)
    public String password;

    public String about;


    public Set<Role> roles=new HashSet<>();

}
