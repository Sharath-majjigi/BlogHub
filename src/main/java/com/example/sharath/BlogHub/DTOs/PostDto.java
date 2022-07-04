package com.example.sharath.BlogHub.DTOs;

import com.example.sharath.BlogHub.Models.Category;
import com.example.sharath.BlogHub.Models.Comment;
import com.example.sharath.BlogHub.Models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    public Long id;

    @NotBlank
    @Size(max = 100,message = "Title should not exceed 20 characters")
    public String title;

    private Date postDate;

    @NotBlank
    @Size(max = 2000)
    public String content;

    public String imageName;

    public Category category;

    public User user;

    public List<Comment> comments=new ArrayList<>();
}
