package com.collections1.aghw.hwcollections1;

import com.collections1.aghw.hwcollections1.exception.DepartmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService departmentService;
    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employees = Arrays.asList(
                new Employee("Anna", "Yamada", 200000, 1),
                new Employee("Marin", "Kitagawa", 300000, 2),
                new Employee("Mahiru", "Shiina", 600000, 3),
                new Employee("Any", "Employee", 100000, 1)
        );
        when(employeeService.getAllEmployees()).thenReturn(employees);
    }
    @Test
    void getEmployeesByDepartment_success() {
        List<Employee> department1Employees = departmentService.getEmployeesByDepartment(1);
        assertEquals(2, department1Employees.size());
    }

    @Test
    void getEmployeesByDepartment_notFound() {
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getEmployeesByDepartment(4));
    }

    @Test
    void getDepartmentSalarySum_success() {
        int salarySum = departmentService.getDepartmentSalarySum(1);
        assertEquals(300000, salarySum);
    }

    @Test
    void getDepartmentSalarySum_notFound() {
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getDepartmentSalarySum(4));
    }

    @Test
    void getMaxSalaryEmployee_success() {
        Employee maxSalaryEmployee = departmentService.getMaxSalaryEmployee(3);
        assertNotNull(maxSalaryEmployee);
        assertEquals(600000, maxSalaryEmployee.getSalary());
    }

    @Test
    void getMaxSalaryEmployee_notFound() {
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getMaxSalaryEmployee(4));
    }

    @Test
    void getMinSalaryEmployee_success() {
        Employee minSalaryEmployee = departmentService.getMinSalaryEmployee(1);
        assertNotNull(minSalaryEmployee);
        assertEquals(100000, minSalaryEmployee.getSalary());
    }

    @Test
    void getMinSalaryEmployee_notFound() {
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getMinSalaryEmployee(4));
    }

    @Test
    void getAllEmployeesGroupedByDepartment_success() {
        Map<Integer, List<Employee>> employeesGroupedByDepartment = departmentService.getAllEmployeesGroupedByDepartment();
        assertEquals(3, employeesGroupedByDepartment.size());
        assertEquals(2, employeesGroupedByDepartment.get(1).size());
        assertEquals(1, employeesGroupedByDepartment.get(2).size());
        assertEquals(1, employeesGroupedByDepartment.get(3).size());
    }
}
