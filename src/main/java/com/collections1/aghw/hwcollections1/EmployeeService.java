package com.collections1.aghw.hwcollections1;
import com.collections1.aghw.hwcollections1.exception.EmployeeAlreadyAddedException;
import com.collections1.aghw.hwcollections1.exception.EmployeeNotFoundException;
import com.collections1.aghw.hwcollections1.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    public static final int MAX_EMPLOYEES = 10;
    public EmployeeService() {
        employees.put("Anna Yamada", new Employee("Anna", "Yamada"));
        employees.put("Marin Kitagava", new Employee("Marin", "Kitagawa"));
        employees.put("Mahiru Shiina", new Employee("Mahiru", "Shiina"));
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
}
