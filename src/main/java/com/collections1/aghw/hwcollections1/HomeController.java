package com.collections1.aghw.hwcollections1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "для добавления сотрудника - /employees/add?firstName=имя&lastName=фамилия&salary=сумма зп&department=номер отдела || "
                + "для удаления сотрудника - /employees/remove?firstName=имя&lastName=фамилия || "
                + "поиск по имени фамилии - /employees/search?firstName=имя&lastName=фамилия || "
                + "список всех сотрудников - /employees || "
                + "сотрудник с макс зп в отделе - /employees/max-salary?department=номер отдела || "
                + "сотрудник с мин зп в отделе - /employees/min-salary?department=номер отдела || "
                + "все сотрудники отдела - /employees/department?department=номер отдела || "
                + "список сотрудников с разделением по отделам - /employees/all-by-departments";
    }
}
