package icu.xuyijie.springbootthymeleaf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 徐一杰
 * @date 2023/7/22 13:59
 * @description 员工
 */
@Data
public class Employee {
    private Integer id;
    private String name;
    private String duty;
    private String sex;
    private Date createTime;
    private Date editTime;
    private Boolean isDepart;
    private Boolean isDelete;
}
