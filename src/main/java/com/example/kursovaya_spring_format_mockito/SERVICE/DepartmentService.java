package com.example.kursovaya_spring_format_mockito.SERVICE;

import com.example.kursovaya_spring_format_mockito.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService emploeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.emploeeService = employeeService;
    }

    public Set<Integer> getExistingDepartments() {
        return emploeeService.getAllEmploees().stream().map(Employee::getDepartment).collect(Collectors.toSet());
    }


    public List<Employee> getEmployeeesFromDepartment(int departmentId) {
        return emploeeService.getAllEmploees().stream().
                filter(emploee -> emploee.getDepartment() == departmentId).collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getEmployeeesByDepartment() {
        return getExistingDepartments().stream().
                collect(Collectors.toMap(dept -> dept, this::getEmployeeesFromDepartment));
    }

    public int getSalarySumOfDepartment(int departmentId) {
        return getEmployeeesFromDepartment(departmentId).stream().mapToInt(Employee::getSalary).sum();
    }

    public int getMinSalaryOfDepartment(int departmentId) {
        return getEmployeeesFromDepartment(departmentId).stream().mapToInt(Employee::getSalary).min().orElseThrow();
    }

    public int getMaxSalaryOfDepartment(int departmentId) {
        return getEmployeeesFromDepartment(departmentId).stream().mapToInt(Employee::getSalary).max().orElseThrow();
    }

}
