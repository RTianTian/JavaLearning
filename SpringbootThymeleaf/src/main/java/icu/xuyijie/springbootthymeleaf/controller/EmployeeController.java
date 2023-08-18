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

    /**
     * 跳转到编辑页面
     *
     * @param model 页面模型
     * @return 跳转的页面文件名
     */
    @GetMapping("/goEditEmployee/{id}")
    public String goEditEmployee(Model model, @PathVariable Integer id) {
        model.addAttribute("title", "编辑");
        //编辑的时候需要展示出数据原有的内容
        Employee employee = employeeMapper.getById(id);
        model.addAttribute("employee", employee);
        //跳转到编辑页面（新增和编辑共用一个页面）
        return "addEmployee";
    }

    /**
     * 跳转到新增页面
     *
     * @param model 页面模型
     * @return 跳转的页面文件名
     */
    @GetMapping("/goAddEmployee")
    public String goAddEmployee(Model model) {
        model.addAttribute("title", "新增");
        //新增虽然不需要展示数据，但是也要new一个Employee传过去，防止页面上的${employee.id}报错
        model.addAttribute("employee", new Employee());
        //跳转到编辑页面（新增和编辑共用一个页面）
        return "addEmployee";
    }

    @PostMapping("/addOrEditEmployee")
    public String addOrEditEmployee(Employee employee) {
        //id为null的时候，说明是新增操作
        if (employee.getId() == null) {
            employeeMapper.insert(employee);
        } else {
            employeeMapper.update(employee);
        }
        //保存后重定向，重新获取列表数据
        return "redirect:/employee";
    }

}
