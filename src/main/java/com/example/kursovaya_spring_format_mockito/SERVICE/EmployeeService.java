package com.example.kursovaya_spring_format_mockito.SERVICE;

import com.example.kursovaya_spring_format_mockito.Model.Employee;
import com.example.kursovaya_spring_format_mockito.Record.EmployeeRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmploees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (StringUtils.isEmpty(employeeRequest.getFirstName()) || StringUtils.isBlank(employeeRequest.getFirstName())) {
            throw new RuntimeException("Введите правильно имя сотрудника");
        } else if (StringUtils.isEmpty(employeeRequest.getLastName()) || StringUtils.isBlank(employeeRequest.getLastName())) {
            throw new RuntimeException("Введите правильно фамилию сотрудника");
        }
        Employee employee = new Employee(
                StringUtils.capitalize(employeeRequest.getFirstName()),
                StringUtils.capitalize(employeeRequest.getLastName()),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        this.employees.put(employee.getId(), employee);
        return employee;
    }
    public int getSalarySum( ) {
        return employees.values().stream().mapToInt(e ->  e.getSalary()).sum();
    }

    public Optional<Employee> getEmloyeeSalaryMax( ) {
        return employees.values().stream().max(Comparator.comparingInt(Employee::getSalary));
    }
    public Optional<Employee> getEmloyeeSalaryMin( ) {
        return employees.values().stream().min(Comparator.comparingInt(Employee::getSalary));
    }
    public List<Employee> getEmloyeeSalaryHigh( ) {
        double middle = employees.values().stream()
                .mapToInt(Employee -> Employee.getSalary())
                .average().orElse(0);
        return employees.values().stream()
                .filter(e->e.getSalary()>middle).collect(Collectors.toList());
    }
}
