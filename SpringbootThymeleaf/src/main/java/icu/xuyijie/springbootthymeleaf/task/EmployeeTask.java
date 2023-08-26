package icu.xuyijie.springbootthymeleaf.task;

import icu.xuyijie.springbootthymeleaf.entity.Employee;
import icu.xuyijie.springbootthymeleaf.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class EmployeeTask {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 每分钟检查一次数据库
     */
    @Scheduled(fixedDelay = 30000)
    public void printHello() {
        //当前时间的毫秒
        long nowTime = new Date().getTime();

        //把所有员工查询出来
        List<Employee> employeeList = employeeMapper.searchEmployeeList(new Employee());

        //循环员工列表，用每个员工的合同期限字段，和当前时间做对比
        for (Employee employee : employeeList) {
            Date contractDeadline = employee.getContractDeadline();
            if (contractDeadline != null) {
                //合同期限的毫秒
                long contractDeadlineTime = contractDeadline.getTime();
                //期限小于当前时间，更新员工状态为离职，否则不做任何操作
                if (contractDeadlineTime < nowTime) {
                    employee.setIsDepart(true);
                    employeeMapper.update(employee);
                }
            }
        }
    }

}
