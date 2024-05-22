package com.collections1.aghw.hwcollections1;
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
        if (employeeService.addEmployee(employee)) {
            return "Employee added successfully.";
        } else {
            return "Can't add more employees, coz limit has been reached.";
        }
    }
    @DeleteMapping
    public String removeEmployee(@RequestBody Employee employee) {
        if (employeeService.removeEmployee(employee)) {
            return "Employee removed successfully.";
        } else {
            return "Employee not found.";
        }
    }
    @GetMapping("/search")
    public Employee findEmployeeByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployeeByFullName(firstName, lastName);
    }
}
