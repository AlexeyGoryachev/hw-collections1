package com.collections1.aghw.hwcollections1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.addEmployee(employee);
        return ResponseEntity.ok("Employee added succesfully");
    }
    @GetMapping("/remove")
    public ResponseEntity<String> removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.removeEmployee(employee);
        return ResponseEntity.ok("Employee removed successfully");
    }
    @GetMapping("/search")
    public ResponseEntity<Employee> findEmployeeByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = employeeService.findEmployeeByFullName(firstName, lastName);
        return ResponseEntity.ok(employee);
    }
}
