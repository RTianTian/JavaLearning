package com.tiantian.employeemanagement.controller;

import com.tiantian.employeemanagement.entity.Employee;
import com.tiantian.employeemanagement.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author 徐一杰
 * @date 2023/7/22 14:15
 * @description 员工操作相关API
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/getEmployeeList")
    public List<Employee> getEmployeeList(Employee employee) {
        return employeeMapper.searchEmployeeList(employee);
    }

    @PostMapping("/delEmployee")
    public Employee delEmployee(@RequestBody Employee employee) {
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
    @GetMapping("/goEditEmployee/{id}")
    public Employee goEditEmployee(@PathVariable Integer id) {
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
//    @RequestBody主要用来接收前端传递给后端的json字符串中的数据的
    public boolean jsonAddOrEditEmployee(@RequestBody Employee employee) {
        System.out.println("这是我打印的" + employee);
        //id为null的时候，说明是新增操作
        if (employee.getId() == null) {
            employeeMapper.insert(employee);
        } else {
            employeeMapper.update(employee);
        }
        return true;
    }

    @PostMapping("/entryEmployee")
    public void entryEmploy(@RequestBody Employee employee){
        employeeMapper.update(employee);
    }

    public static void main(String[] args) {
        // id的值是31
        // name的值是天天
        String params = "?id=31&name=天天&age=20";
     String[] arr = params.split("&");
        System.out.println("1"+Arrays.toString (arr));
        for (int i=0;i<arr.length;i++){
           String[] arr2= arr[i].split("=");
           System.out.println("2"+Arrays.toString(arr2));
            System.out.println(arr2[0].replace("?", "") + "的值为" + arr2[1]);
        }

    }

}
