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
 * @description
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

    @PostMapping("/addOrEditEmployee")
    public String addOrEditEmployee(Employee employee) {
        if (employee.getId() == null) {
            employeeMapper.insert(employee);
        } else {
            employeeMapper.update(employee);
        }
        return "redirect:/employee";
    }

    @DeleteMapping("/delEmployee")
    public String delEmployee(Model model, @RequestBody Employee employee) {
        employeeMapper.delete(employee);
        List<Employee> employeeList = employeeMapper.searchEmployeeList(employee);
        model.addAttribute("employeeList", employeeList);
        //局部刷新
        return "employeeIndex::employeeTable";
    }

    @GetMapping("/departEmployee/{id}")
    public String departEmployee(@PathVariable int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setIsDepart(true);
        employeeMapper.departOrEntryEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("/entryEmployee/{id}")
    public String entryEmployee(@PathVariable int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setIsDepart(false);
        employeeMapper.departOrEntryEmployee(employee);
        return "redirect:/employee";
    }

}
