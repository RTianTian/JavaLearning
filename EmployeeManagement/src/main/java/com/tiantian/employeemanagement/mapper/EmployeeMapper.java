package com.tiantian.employeemanagement.mapper;

import com.tiantian.employeemanagement.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 天天
 * @date 2023/7/22 14:01
 * @description
 */
@Mapper
public interface EmployeeMapper {
    Employee getById(@Param("id") int id);

    List<Employee> searchEmployeeList(Employee employee);

    int insert(Employee employee);

    int update(Employee employee);


}
