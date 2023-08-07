package com.tiantian.springrest.entity;

import lombok.Data;

import java.util.Date;

@Data  //相当于get set还有toSting()方法
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer age;
    private String hometown;
    private Date createTime;

}
