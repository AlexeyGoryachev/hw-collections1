package com.collections1.aghw.hwcollections1;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> findEmployeesByDepartment(int departmentId) {
        return employeeService.findEmployeesByDep(departmentId);
    }
    public int getDepartmentSalarySum(int departmentId) {
        return findEmployeesByDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee findEmployeeWithMaxSalaryByDepartment(int departmentId) {
        return employeeService.findEmployeeWithMaxSalaryByDep(departmentId);
    }

    public Employee findEmployeeWithMinSalaryByDepartment(int departmentId) {
        return employeeService.findEmployeeWithMinSalaryByDep(departmentId);
    }

    public Map<Integer, List<Employee>> findAllEmployeesGroupedByDepartment() {
        return employeeService.findAllEmployeesByDep();
    }
}
