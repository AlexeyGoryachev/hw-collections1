package com.collections1.aghw.hwcollections1;

import com.collections1.aghw.hwcollections1.exception.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;
@Autowired
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesByDepartment(int departmentId) {
        List<Employee> employees = employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
        if (employees.isEmpty()) {
            throw new DepartmentNotFoundException("No employees foun in department " + departmentId);
        }
        return employees;
    }

    public int getDepartmentSalarySum(int departmentId) {
        return getEmployeesByDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getMaxSalaryEmployee(int departmentId) {
        return getEmployeesByDepartment(departmentId).stream()
                .max((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()))
                .orElseThrow(() -> new DepartmentNotFoundException("No employees found in department " + departmentId));
    }
    public Employee getMinSalaryEmployee(int departmentId) {
        return getEmployeesByDepartment(departmentId).stream()
                .min((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()))
                .orElseThrow(() -> new DepartmentNotFoundException("No employees found in department " + departmentId));
    }

    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}