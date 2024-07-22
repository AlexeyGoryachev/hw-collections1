package com.collections1.aghw.hwcollections1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "для добавления сотрудника - GET http://localhost:8080/employees/add?firstName=имя&lastName=фамилия&salary=сумма зп&department=номер отдела || "
                + "для удаления сотрудника - GET http://localhost:8080/employees/remove?firstName=имя&lastName=фамилия || "
                + "поиск по имени фамилии - GET http://localhost:8080/employees/search?firstName=имя&lastName=фамилия || "
                + "список всех сотрудников - GET http://localhost:8080/employees || "
                + "сотрудник с макс зп в отделе - GET http://localhost:8080/employees/max-salary?department=номер отдела || "
                + "сотрудник с мин зп в отделе - GET http://localhost:8080/employees/min-salary?department=номер отдела || "
                + "все сотрудники отдела - GET http://localhost:8080/employees/department?department=номер отдела || "
                + "список сотрудников с разделением по отделам - GET http://localhost:8080/employees/all-by-departments"
                + "далее КОМАНДЫ ДЛЯ DepartmentController || "
                + "вывести список сотрудников из отдела - GET http://localhost:8080/department/№отдела/employees || "
                + "вывести сумму зарплат сотрудников отдела - GET http://localhost:8080/department/№отдела/salary/sum ||"
                + "вывести сотрудника с максимальной ЗП из отдела - GET http://localhost:8080/department/№отдела/salary/max || "
                + "вывести сотрудника с минимальной ЗП из отдела - GET http://localhost:8080/department/№отдела/salary/min || "
                + "вывести список всех сотрудников с разделением по отделам - GET http://localhost:8080/department/employees.";
    }
}
