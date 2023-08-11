package icu.xuyijie.springbootthymeleaf.controller;

import icu.xuyijie.springbootthymeleaf.entity.Employee;
import icu.xuyijie.springbootthymeleaf.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping
    public String employee(Model model, Employee employee) {
        List<Employee> employeeList = employeeMapper.searchEmployeeList(employee);
        model.addAttribute("employeeList", employeeList);
        return "employeeIndex";
    }

    @GetMapping("/departEmployee/{id}")
    public String departEmployee(@PathVariable int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setIsDepart(true);
        employeeMapper.update(employee);
        return "redirect:/employee";
    }

    @GetMapping("/entryEmployee/{id}")
    public String entryEmployee(@PathVariable int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setIsDepart(false);
        employeeMapper.update(employee);
        return "redirect:/employee";
    }

    @DeleteMapping("/delEmployee")
    public String delEmployee(Model model, @RequestBody Employee employee) {
        employee.setIsDelete(true);
        employeeMapper.update(employee);
        List<Employee> employeeList = employeeMapper.searchEmployeeList(employee);
        model.addAttribute("employeeList", employeeList);
        //局部刷新
        return "employeeIndex::employeeTable";
    }

    @GetMapping("/goAddOrEditEmployee/{id}")
    public String goAddOrEditEmployee(Model model, @PathVariable Integer id) {
        if (id == null) {
            model.addAttribute("title", "新增");
        } else {
            model.addAttribute("title", "编辑");
            Employee employee = employeeMapper.getById(id);
            model.addAttribute("employee", employee);
        }
        return "addEmployee";
    }

    @PostMapping("/addOrEditEmployee/{id}")
    public String addOrEditEmployee(@PathVariable Integer id, Employee employee) {
        if (id == null) {
            employeeMapper.insert(employee);
        } else {
            employeeMapper.update(employee);
        }
        return "redirect:/employee";
    }

}
