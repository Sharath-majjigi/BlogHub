package com.example.sharath.BlogHub.Models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Role {

    @Id
    private long id;

    private String name;
}
