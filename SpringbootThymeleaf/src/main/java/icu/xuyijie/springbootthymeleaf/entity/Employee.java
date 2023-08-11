package icu.xuyijie.springbootthymeleaf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 徐一杰
 * @date 2023/7/22 13:59
 * @description
 */
@Data
public class Employee {
    private Integer id;
    private String username;
    private String duty;
    private Date createTime;
    private Date editTime;
    private Boolean isDepart;
    private Boolean isDelete;
}
