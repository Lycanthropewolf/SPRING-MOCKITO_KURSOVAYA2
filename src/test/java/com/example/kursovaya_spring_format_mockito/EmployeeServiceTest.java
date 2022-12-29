package com.example.kursovaya_spring_format_mockito;

import com.example.kursovaya_spring_format_mockito.Model.Employee;
import com.example.kursovaya_spring_format_mockito.Record.EmployeeRequest;
import com.example.kursovaya_spring_format_mockito.SERVICE.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.Collection;
import java.util.stream.Stream;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        Stream.of(new EmployeeRequest("Dmitrii", "Smirnov", 1, 5000), new EmployeeRequest("Artem", "Geltonog", 2, 60000), new EmployeeRequest("Marina", "Nepomnu", 2, 20000), new EmployeeRequest("Nikita", "Nikitin", 3, 15000), new EmployeeRequest("Alex", "Ivanov", 3, 40000)).forEach(employeeService::addEmployee);
    }

    @Test
    public void addEmployee() {
        EmployeeRequest request = new EmployeeRequest("Valid", "Valid", 3, 50000);
        Employee result = employeeService.addEmployee(request);
        Assertions.assertEquals(request.getFirstName(), result.getFirstName());
        Assertions.assertEquals(request.getLastName(), result.getLastName());
        Assertions.assertEquals(request.getDepartment(), result.getDepartment());
        Assertions.assertEquals(request.getSalary(), result.getSalary());
        Assertions.assertThat(employeeService.getAllEmploees()).contains(result);
    }

    @Test
    public void listEmployee() {
        Collection<Employee> employees=employeeService.getAllEmploees();
        Assertions.assertThat
    }
}
