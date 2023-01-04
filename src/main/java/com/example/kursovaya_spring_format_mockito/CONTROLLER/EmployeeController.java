package com.example.kursovaya_spring_format_mockito.CONTROLLER;

import com.example.kursovaya_spring_format_mockito.Model.Employee;
import com.example.kursovaya_spring_format_mockito.Record.EmployeeRequest;
import com.example.kursovaya_spring_format_mockito.SERVICE.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmploees();
    }

    @PostMapping("/employees")
    public Employee createEmployeee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("employees/salary/sum")
    public int getSalarySum() {
        return this.employeeService.getSalarySum();
    }

    @GetMapping("employees/salary/max")
    public Employee getSalaryMax() {
        return this.employeeService.getEmployeeSalaryMax();
    }

    @GetMapping("employees/salary/min")
    public Employee getSalaryMin() {
        return this.employeeService.getEmployeeSalaryMin();
    }

    @GetMapping("employees/highSalary")
    public List<Employee> getSalaryHigh() {
        return this.employeeService.getEmloyeeSalaryHigh();
    }


}
