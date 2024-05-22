package com.collections1.aghw.hwcollections1;
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
    public boolean addEmployee(Employee employee) {
//        добавляем сотрудника
        if (employees.size() < MAX_EMPLOYEES) {
            employees.add(employee);
            return true;
        } else {
            System.out.println("Can't add more employees, coz limit has been reached.");
            return false;
        }
    }
    public boolean removeEmployee(Employee employee) {
//        удаляем сотрудника
        return employees.remove(employee);
    }
    public List<Employee> getAllEmployees() {
//        смотрим список сотрудников
        return new ArrayList<>(employees);
    }
    public Employee findEmployeeByFullName(String firstName, String lastName) {
//        ищем сотрудника по имени и фамилии
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        return null;
    }
    public int getEmployeeCount() {
//        смотрим количество сотрудников
        return employees.size();
    }
}
