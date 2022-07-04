package com.example.sharath.BlogHub.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryTitle;

    private String categoryDescription;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
