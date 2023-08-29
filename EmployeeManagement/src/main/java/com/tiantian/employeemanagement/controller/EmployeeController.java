package com.tiantian.employeemanagement.controller;

import com.tiantian.employeemanagement.entity.Employee;
import com.tiantian.employeemanagement.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 徐一杰
 * @date 2023/7/22 14:15
 * @description 员工操作相关API
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeMapper employeeMapper;


    @GetMapping("/getEmployeeList")
    @ResponseBody
    public List<Employee> getEmployeeList(Employee employee) {
        List<Employee> employeeList = employeeMapper.searchEmployeeList(employee);
        return employeeList;
    }



    @PostMapping("/delEmployee2")
    @ResponseBody
    public Employee delEmployee2(@RequestBody Employee employee) {
        System.out.println(employee);
        employee.setIsDelete(true);
        employeeMapper.update(employee);
        return employee;
    }

    /**
     * 编辑页面获取数据
     *
     * @param id id
     * @return 跳转的页面文件名
     */
    @GetMapping("/goEditEmployee2/{id}")
    @ResponseBody
    public Employee goEditEmployee2(@PathVariable Integer id) {
        return employeeMapper.getById(id);
    }

    /**
     * 表单提交方式
     *
     * @param employee 表单信息
     * @return 保存结果
     */
    @PostMapping("/formAddOrEditEmployee")
    public String formAddOrEditEmployee(Employee employee) {
        //id为null的时候，说明是新增操作
        if (employee.getId() == null) {
            employeeMapper.insert(employee);
        } else {
            employeeMapper.update(employee);
        }
        return "保存成功";
    }

    /**
     * json提交方式
     *
     * @param employee json信息
     * @return 保存结果
     */
    @PostMapping("/jsonAddOrEditEmployee")
    public boolean jsonAddOrEditEmployee(@RequestBody Employee employee) {
        //id为null的时候，说明是新增操作
        if (employee.getId() == null) {
            employeeMapper.insert(employee);
        } else {
            employeeMapper.update(employee);
        }
        return true;
    }

}
