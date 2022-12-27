package com.example.kursovaya_spring_format_mockito.CONTROLLER;

import com.example.kursovaya_spring_format_mockito.Model.Employee;
import com.example.kursovaya_spring_format_mockito.SERVICE.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public String getExistingDepartments() {
        return "Existing Departments:" + departmentService.getExistingDepartments().toString();
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployeeesByDepartment() {
        return departmentService.getEmployeeesByDepartment();
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> getEmployeeesFromDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getEmployeeesFromDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSalarySumOfDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getSalarySumOfDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public int getMinSalaryOfDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getMinSalaryOfDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalaryOfDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getMaxSalaryOfDepartment(departmentId);
    }

}
