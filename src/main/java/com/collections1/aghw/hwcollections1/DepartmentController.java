package com.collections1.aghw.hwcollections1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable int id) {
        List<Employee> employees = departmentService.getEmployeesByDepartment(id);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}/salary/sum")
    public ResponseEntity<Integer> getDepartmentSalarySum(@PathVariable int id) {
        int salarySum = departmentService.getDepartmentSalarySum(id);
        return ResponseEntity.ok(salarySum);
    }

    @GetMapping("/{id}/salary/max")
    public ResponseEntity<Employee> getDepartmentMaxSalary(@PathVariable int id) {
        Employee employee = departmentService.getMaxSalaryEmployee(id);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/{id}/salary/min")
    public ResponseEntity<Employee> getDepartmentMinSalary(@PathVariable int id) {
        Employee employee = departmentService.getMinSalaryEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<Map<Integer, List<Employee>>> getAllEmployeesGroupedByDepartment() {
        Map<Integer, List<Employee>> employeesGroupedByDepartment = departmentService.getAllEmployeesGroupedByDepartment();
        return ResponseEntity.ok(employeesGroupedByDepartment);
    }
}
