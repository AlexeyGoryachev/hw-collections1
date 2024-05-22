package com.collections1.aghw.hwcollections1;

import com.collections1.aghw.hwcollections1.exception.EmployeeAlreadyAddedException;
import com.collections1.aghw.hwcollections1.exception.EmployeeNotFoundException;
import com.collections1.aghw.hwcollections1.exception.EmployeeStorageIsFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @PostMapping
    public String addEmployee(@RequestBody Employee employee) {
        try {
            employeeService.addEmployee(employee);
            return "Employee added successfully";
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            return e.getMessage();
        }
    }
    @DeleteMapping
    public String removeEmployee(@RequestBody Employee employee) {
        try {
            employeeService.removeEmployee(employee);
            return "Employee removed successfully.";
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }
    @GetMapping("/search")
    public Employee findEmployeeByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            return employeeService.findEmployeeByFullName(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
