package com.collections1.aghw.hwcollections1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HwCollections1Application {

	public static void main(String[] args) {
		SpringApplication.run(HwCollections1Application.class, args);
		Employee[] employees = {
				new Employee("Anna", "Yamada"),
				new Employee("Marin", "Kitagava"),
				new Employee("Mahiru", "Shiina")
		};
		for (int i = 0; i < employees.length; i++) {
			System.out.println(employees[i]);
		}
	}

}
