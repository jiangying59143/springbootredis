package com.example.springbootredis.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Integer id;
    private String name;
    private Double score;
    private Date birthday;
}
