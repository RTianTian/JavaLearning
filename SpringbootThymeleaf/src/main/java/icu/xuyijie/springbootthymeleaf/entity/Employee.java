package icu.xuyijie.springbootthymeleaf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date editTime;
    private Boolean isDepart;
    private Boolean isDelete;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date contractDeadline;

}
