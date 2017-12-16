package com.tchokonthe.swagger.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User {

    @ApiModelProperty(notes = "Provides user id", required = true)
    private long id;

    @ApiModelProperty(notes = "Provides user name", required = true)
    private String name;

    @ApiModelProperty(notes = "Provides user age", required = true)
    private int age;

    @ApiModelProperty(notes = "Provides user salary", required = true)
    private double salary;

    public User() { id = 0; }

    public User(long id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
