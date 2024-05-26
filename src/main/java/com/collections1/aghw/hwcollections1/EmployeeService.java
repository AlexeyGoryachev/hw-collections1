package com.collections1.aghw.hwcollections1;
import com.collections1.aghw.hwcollections1.exception.EmployeeAlreadyAddedException;
import com.collections1.aghw.hwcollections1.exception.EmployeeNotFoundException;
import com.collections1.aghw.hwcollections1.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
//    объявляем константу с максимальным допустимым кол-вом сотрудников
    public static final int MAX_EMPLOYEES = 10;
    public EmployeeService() {
//        инициализируем сотрудников в листе
        employees.add(new Employee("Anna", "Yamada"));
        employees.add(new Employee("Marin", "Kitagawa"));
        employees.add(new Employee("Mahiru", "Shiina"));
    }
//    для добавления нового сотрудника ввести http://localhost:8080/employees/add?firstName=имя&lastName=фамилия
    public boolean addEmployee(Employee employee) {
//        добавляем сотрудника (добавил выброс исключений)
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Can't add more employees, coz limit has been reached.");
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Employee already added.");
        }
        employees.add(employee);
        return true;
    }
//    для удаления сотрудника ввести http://localhost:8080/employees/remove?firstName=имя&lastName=фамилия
    public boolean removeEmployee(Employee employee) {
//        удаляем сотрудника (добавил выброс исключений)
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException("Employee not found.");
        }
        return true;
    }
//    для получения списка сотрудников ввести http://localhost:8080/employees
    public List<Employee> getAllEmployees() {
//        смотрим список сотрудников
        return new ArrayList<>(employees);
    }
//    для поиска сотрудника ввести http://localhost:8080/employees/search?firstName=имя&lastName=фамилия
    public Employee findEmployeeByFullName(String firstName, String lastName) {
//        ищем сотрудника по имени и фамилии
        return employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found."));
    }
    public int getEmployeeCount() {
//        смотрим количество сотрудников
        return employees.size();
    }
}
