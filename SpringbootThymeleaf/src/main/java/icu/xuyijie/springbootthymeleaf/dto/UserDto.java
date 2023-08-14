package icu.xuyijie.springbootthymeleaf.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 徐一杰
 * @date 2023/7/22 13:59
 * @description 登录用户传输实体
 */
@Data
public class UserDto implements Serializable {
    private String username;
    private String mobilePhone;
    private String password;
    private String rePassword;
}
