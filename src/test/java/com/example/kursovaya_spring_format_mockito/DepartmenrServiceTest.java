package com.example.kursovaya_spring_format_mockito;

import com.example.kursovaya_spring_format_mockito.Model.Employee;
import com.example.kursovaya_spring_format_mockito.SERVICE.DepartmentService;
import com.example.kursovaya_spring_format_mockito.SERVICE.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmenrServiceTest {

    private final List<Employee> employees = List.of(
            new Employee("Dmitrii", "Smirnow", 0, 30000),
            new Employee("Artem", "Geltonogow", 2, 40000),
            new Employee("Maria", "Dron", 1, 50000),
            new Employee("Nikita", "Nikitin", 2, 100000),
            new Employee("Oleg", "Vain", 0, 70000)
    );

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        when(employeeService.getAllEmploees()).thenReturn(employees);
    }

    @Test
    void getEmployeesByDepartment() {
        Collection<Employee> employeeList = this.departmentService.getEmployeesFromDepartment(0);
        Assertions.assertThat(employeeList)
                .hasSize(5)
                .contains(
                        employees.get(0),
                        employees.get(1),
                        employees.get(2));
    }

    @Test
    void sumSalariesByDepartment() {
        int sum = this.departmentService.getSalarySumOfDepartment(1);
        Assertions.assertThat(sum).isEqualTo(50000);
    }

    @Test
    void maxSalaryInDepartment() {
        int max = this.departmentService.getMaxSalaryOfDepartment(1);
        Assertions.assertThat(max).isEqualTo(50000);
    }

    @Test
    void minSalaryInDepartment() {
        int min = this.departmentService.getMinSalaryOfDepartment(0);
        Assertions.assertThat(min).isEqualTo(30000);
    }

    @Test
    void groupedEmployees() {
        Map<Integer, List<Employee>> groupedEmployees = this.departmentService.getEmployeeesByDepartment();
        Assertions.assertThat(groupedEmployees).hasSize(2)
                .containsEntry(1, List.of(employees.get(0), employees.get(1), employees.get(2)))
                .containsEntry(2, List.of(employees.get(3), employees.get(5)));
    }

    @Test
    void WhenEmployeesThenGroupByReturnEmptyMap() {
        when(employeeService.getAllEmploees()).thenReturn(List.of());
        Map<Integer, List<Employee>> groupedEmployees = this.departmentService.getEmployeeesByDepartment();
        Assertions.assertThat(groupedEmployees).isEmpty();
    }


}
