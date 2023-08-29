package com.tiantian.employeemanagement.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 天天
 * @date 2023/8/29 14:42
 * @description 登录用户
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String mobilePhone;
    private String password;
    private Date createTime;
    private Date editTime;
    private Boolean isDelete;
}
