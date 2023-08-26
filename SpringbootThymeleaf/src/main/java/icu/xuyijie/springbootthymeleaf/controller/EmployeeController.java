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

    @GetMapping("/getEmployeeList")
    @ResponseBody
    public List<Employee> getEmployeeList(Employee employee) {
        List<Employee> employeeList = employeeMapper.searchEmployeeList(employee);
        return employeeList;
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

    @PostMapping("/delEmployee2")
    @ResponseBody
    public Employee delEmployee2(@RequestBody Employee employee) {
        System.out.println(employee);
        employee.setIsDelete(true);
        employeeMapper.update(employee);
        return employee;
    }

    /**
     * 跳转到编辑页面
     *
     * @param model 页面模型
     * @return 跳转的页面文件名
     */
    @GetMapping("/goEditEmployee/{id}")
    public String goEditEmployee(Model model, @PathVariable Integer id) {
        //不知道title显示在哪，就去页面搜一下title，看这个方法最后一行，return到addEmployee页面，就去这个页面搜索
        model.addAttribute("title", "编辑");
        //编辑的时候需要展示出数据原有的内容
        Employee employee = employeeMapper.getById(id);
        model.addAttribute("employee", employee);
        //跳转到编辑页面（新增和编辑共用一个页面）
        return "addEmployee";
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
        //保存后重定向(重定向就是改变浏览器url，相当于输入127.0.0.1:8080/employee后回车)，重新获取列表数据
        return "redirect:/employee";
    }

    @PostMapping("/addOrEditEmployee2")
    @ResponseBody
    public String addOrEditEmployee2(Employee employee) {
        //id为null的时候，说明是新增操作
        if (employee.getId() == null) {
            employeeMapper.insert(employee);
        } else {
            employeeMapper.update(employee);
        }
        return "保存成功";
    }

    @PostMapping("/addOrEditEmployee3")
    @ResponseBody
    public boolean addOrEditEmployee3(@RequestBody Employee employee) {
        //id为null的时候，说明是新增操作
        if (employee.getId() == null) {
            employeeMapper.insert(employee);
        } else {
            employeeMapper.update(employee);
        }
        return true;
    }

    public static void main(String[] args) {
        //给你演示一下employee.id为社么报错
        //如果没有这一行model.addAttribute("employee", new Employee());相当于传给页面的employee就是null
        Employee employee = null;
        Employee employee2 = new Employee();
        //正常运行
        System.out.println(employee2.getId());
        //下面会报错NullPointException
        System.out.println(employee.getId());
    }

}
