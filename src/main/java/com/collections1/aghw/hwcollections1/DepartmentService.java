package com.collections1.aghw.hwcollections1;

import com.collections1.aghw.hwcollections1.exception.DepartmentNotFoundException;
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
        try {
            return employeeService.findEmployeesByDep(departmentId);
        } catch (DepartmentNotFoundException e) {
            throw new DepartmentNotFoundException("Department " + departmentId + " not found.");
        }
    }

    public int getDepartmentSalarySum(int departmentId) {
        return findEmployeesByDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee findEmployeeWithMaxSalaryByDepartment(int departmentId) {
        try {
            return employeeService.findEmployeeWithMaxSalaryByDep(departmentId);
        } catch (DepartmentNotFoundException e) {
            throw new DepartmentNotFoundException("Department " + departmentId + " not found.");
        }
    }
    public Employee findEmployeeWithMinSalaryByDepartment(int departmentId) {
        try {
            return employeeService.findEmployeeWithMinSalaryByDep(departmentId);
        } catch (DepartmentNotFoundException e) {
            throw new DepartmentNotFoundException("Department " + departmentId + " not found.");
        }
    }

    public Map<Integer, List<Employee>> findAllEmployeesByDepartment() {
        return employeeService.findAllEmployeesByDep();
    }
}