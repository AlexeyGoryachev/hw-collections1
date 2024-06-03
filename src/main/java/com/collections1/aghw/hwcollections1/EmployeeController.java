package com.collections1.aghw.hwcollections1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> addEmployee(@RequestParam String firstName,
                                              @RequestParam String lastName,
                                              @RequestParam int salary,
                                              @RequestParam int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        employeeService.addEmployee(employee);
        return ResponseEntity.ok("Employee added succesfully");
    }

    @GetMapping("/remove")
    public ResponseEntity<String> removeEmployee(@RequestParam String firstName,
                                                 @RequestParam String lastName) {
//        при удалении сотрудников, их зарплату и номер отдела можно не учитывать, поэтому ставим "0".
        Employee employee = new Employee(firstName, lastName, 0, 0);
        employeeService.removeEmployee(employee);
        return ResponseEntity.ok("Employee removed successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<Employee> findEmployeeByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = employeeService.findEmployeeByFullName(firstName, lastName);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/max-salary")
    public ResponseEntity<Employee> findEmployeeWithMaxSalaryByDep(@RequestParam int department) {
        Employee employee = employeeService.findEmployeeWithMaxSalaryByDep(department);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/min-salary")
    public ResponseEntity<Employee> findEmployeeWithMinSalaryByDep(@RequestParam int department) {
        Employee employee = employeeService.findEmployeeWithMinSalaryByDep(department);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/department")
    public ResponseEntity<List<Employee>> findEmployeesByDep(@RequestParam int department) {
        List<Employee> employees = employeeService.findEmployeesByDep(department);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/all-by-departments")
    public ResponseEntity<Map<Integer, List<Employee>>> findAllEmployeesByDep() {
        Map<Integer, List<Employee>> employeesByDepartments = employeeService.findAllEmployeesByDep();
        return ResponseEntity.ok(employeesByDepartments);
    }
}
