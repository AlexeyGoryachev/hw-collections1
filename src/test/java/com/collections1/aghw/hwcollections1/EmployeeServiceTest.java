package com.collections1.aghw.hwcollections1;

import com.collections1.aghw.hwcollections1.exception.DepartmentNotFoundException;
import com.collections1.aghw.hwcollections1.exception.EmployeeAlreadyAddedException;
import com.collections1.aghw.hwcollections1.exception.EmployeeNotFoundException;
import com.collections1.aghw.hwcollections1.exception.EmployeeStorageIsFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    void addEmployee_success() {
        Employee newEmployee = new Employee("Any", "Employee", 100000, 1);
        assertTrue(employeeService.addEmployee(newEmployee));
        assertEquals(newEmployee, employeeService.findEmployeeByFullName("Any", "Employee"));
    }

    @Test
    void addEmployee_employeeAlreadyAdded() {
        Employee newEmployee = new Employee("Anna", "Yamada", 200000, 1);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(newEmployee));
    }

    @Test
    void addEmployee_employeeStorageIsFull() {
        for (int i = 0; i < 7; i++) {
            employeeService.addEmployee(new Employee("Test", "Employeer" + i, 100000, 1));
        }
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee(new Employee("Any", "Employee", 100000, 1)));
    }

    @Test
    void removeEmployee_success() {
        Employee employee = new Employee("Anna", "Yamada", 200000, 1);
        assertTrue(employeeService.removeEmployee(employee));
        assertNull(employeeService.findEmployeeByFullName("Anna", "Yamada"));
    }

    @Test
    void removeEmployee_notFound() {
        Employee employee = new Employee("Any", "Employee", 100000, 1);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee(employee));
    }

    @Test
    void findEmployeeByFullName_success() {
        Employee employee = employeeService.findEmployeeByFullName("Anna", "Yamada");
        assertNotNull(employee);
        assertEquals("Anna", employee.getFirstName());
        assertEquals("Yamada", employee.getLastName());
    }

    @Test
    void findEmployeeByFullName_notFound() {
        assertNull(employeeService.findEmployeeByFullName("Any", "Employee"));
    }

    @Test
    void findEmployeesByDep_success() {
        List<Employee> employees = employeeService.findEmployeesByDep(1);
        assertEquals(1, employees.size());
        assertEquals("Anna", employees.get(0).getFirstName());
    }

    @Test
    void findEmployeesByDep_notFound() {
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.findEmployeesByDep(4));
    }

    @Test
    void findEmployeeWithMaxSalaryByDep_success() {
        Employee employee = employeeService.findEmployeeWithMaxSalaryByDep(3);
        assertNotNull(employee);
        assertEquals(600000, employee.getSalary());
    }

    @Test
    void findEmployeeWithMaxSalaryByDep_notFound() {
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.findEmployeeWithMaxSalaryByDep(4));
    }

    @Test
    void findEmployeeWithMinSalaryByDep_success() {
        Employee employee = employeeService.findEmployeeWithMinSalaryByDep(1);
        assertNotNull(employee);
        assertEquals(200000, employee.getSalary());
    }

    @Test
    void findEmployeeWithMinSalaryByDep_notFound() {
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.findEmployeeWithMinSalaryByDep(4));
    }

    @Test
    void getAllEmployees_success() {
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(3, employees.size());
    }
}
