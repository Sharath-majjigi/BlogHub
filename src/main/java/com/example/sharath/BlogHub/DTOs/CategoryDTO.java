package com.example.sharath.BlogHub.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    public Long id;

    @NotNull
    @Size(min = 4,max=30,message = "Title should be min of 4 chars and max of 10 chars")
    public String categoryTitle;

    @NotNull
    @Size(max=200)
    public String categoryDescription;
}
