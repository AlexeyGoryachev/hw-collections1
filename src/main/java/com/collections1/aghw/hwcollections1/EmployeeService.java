package com.collections1.aghw.hwcollections1;
import com.collections1.aghw.hwcollections1.exception.DepartmentNotFoundException;
import com.collections1.aghw.hwcollections1.exception.EmployeeAlreadyAddedException;
import com.collections1.aghw.hwcollections1.exception.EmployeeNotFoundException;
import com.collections1.aghw.hwcollections1.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    public static final int MAX_EMPLOYEES = 10;
    public EmployeeService() {
        employees.put("Anna Yamada", new Employee("Anna", "Yamada", 200000, 1));
        employees.put("Marin Kitagava", new Employee("Marin", "Kitagawa", 300000, 2));
        employees.put("Mahiru Shiina", new Employee("Mahiru", "Shiina", 600000, 3));
    }
    //        инициализируем сотрудников в мапе (ключом является "имя фамилия")
    private String getEmployeeKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
//    при добавлении проверяем наличие ключа в "Мар". выбрасываем исключение, если уже существует или закончились позиции.
    public boolean addEmployee(Employee employee) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Can't add more employees, coz limit has been reached.");
        }
        String key = getEmployeeKey(employee.getFirstName(), employee.getLastName());
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Employee already added.");
        }
        employees.put(key, employee);
        return true;
    }
//    поиск, удаление, добавление сотрудников осуществляется через "Мар", методами использующими ключ "имя фамилия"
    public boolean removeEmployee(Employee employee) {
        String key = getEmployeeKey(employee.getFirstName(), employee.getLastName());
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Employee not found.");
        }
        return true;
    }

    public List<Employee> getAllEmployees() {
        return employees.values().stream().collect(Collectors.toList());
    }

    public Employee findEmployeeByFullName(String firstName, String lastName) {
        String key = getEmployeeKey(firstName, lastName);
        return employees.getOrDefault(key, null);
    }
    public int getEmployeeCount() {
//        смотрим количество сотрудников
        return employees.size();
    }

    public Employee findEmployeeWithMaxSalaryByDep(int department) {
        List<Employee> departmentEmployees = employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        if (departmentEmployees.isEmpty()) {
            throw new DepartmentNotFoundException("No employees found in department" + department);
        }
        return employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employees found in department " + department));
    }
    public Employee findEmployeeWithMinSalaryByDep(int department) {
        List<Employee> departmentEmployees = employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        if (departmentEmployees.isEmpty()) {
            throw new DepartmentNotFoundException("No employees found in department" + department);
        }
        return employees.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employees found in department " + department));
    }
    public List<Employee> findEmployeesByDep(int department) {
        List<Employee> departmentEmployees = employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        if (departmentEmployees.isEmpty()) {
            throw new DepartmentNotFoundException("No employees found in department" + department);
        }
        return departmentEmployees;
    }

    public Map<Integer, List<Employee>> findAllEmployeesByDep() {
        return employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
