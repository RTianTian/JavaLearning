package icu.xuyijie.springbootthymeleaf.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 徐一杰
 * @date 2023/7/22 13:59
 * @description 登录用户
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String mobilePhone;
    private String password;
    private Date createTime;
    private Date editTime;
    private Boolean isDelete;
}
