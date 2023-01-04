package com.example.kursovaya_spring_format_mockito;

import com.example.kursovaya_spring_format_mockito.Model.Employee;
import com.example.kursovaya_spring_format_mockito.Record.EmployeeRequest;
import com.example.kursovaya_spring_format_mockito.SERVICE.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        Stream.of(new EmployeeRequest("Dmitrii", "Smirnov", 1, 5000),
                        new EmployeeRequest("Artem", "Geltonog", 2, 60000),
                        new EmployeeRequest("Marina", "Nepomnu", 2, 20000),
                        new EmployeeRequest("Nikita", "Nikitin", 3, 15000),
                        new EmployeeRequest("Alex", "Ivanov", 3, 40000))
                .forEach(employeeService::addEmployee);
    }

    @Test
    public void addEmployee() {
        EmployeeRequest request = new EmployeeRequest("Valid", "Valid", 3, 50000);
        Employee result = employeeService.addEmployee(request);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getDepartment(), result.getDepartment());
        assertEquals(request.getSalary(), result.getSalary());
        Assertions.assertThat(employeeService.getAllEmploees()).contains(result);
    }

    @Test
    public void listEmployee() {
        Collection<Employee> employees = employeeService.getAllEmploees();
        Assertions.assertThat(employees).hasSize(5);
        Assertions.assertThat(employees).first().extracting(Employee::getFirstName).isEqualTo("Dmitrii");
    }

    @Test
    public void sumOfSalaries() {
        int sum = employeeService.getSalarySum();
        Assertions.assertThat(sum).isEqualTo(140000);
    }

    @Test
    public void employeeWithMaxSalary() {
       Employee employees=employeeService.getEmployeeSalaryMax();
        Assertions.assertThat(employees).isNotNull().extracting(Employee::getFirstName).isEqualTo("Artem");
    }
    @Test
    public void employeeWithMinMSalary() {
        Employee employees=employeeService.getEmployeeSalaryMin();
        Assertions.assertThat(employees).isNotNull().extracting(Employee::getFirstName).isEqualTo("Dmitrii");
    }

}
