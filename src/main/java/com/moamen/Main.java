package com.moamen;

import com.moamen.config.AppConfig;
import com.moamen.exception.EmployeeException;
import com.moamen.model.Employee;
import com.moamen.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();

        context.getEnvironment().setActiveProfiles("dev");

        context.register(AppConfig.class);
        context.refresh();

        EmployeeService employeeService =
                context.getBean(EmployeeService.class);

        System.out.println("\n========================================");
        System.out.println("   EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("========================================");

        UUID employeeId = UUID.randomUUID();

        Employee employee = new Employee(
                employeeId,
                "Moamen",
                "Backend",
                15000
        );

        System.out.println("\nAll Employees:");
        try {
            employeeService.getAllEmployees()
                    .forEach(System.out::println);
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nAdding Employee:");
        employeeService.addEmployee(employee);
        System.out.println(employee);

        System.out.println("\nEmployee By ID:");
        Employee foundEmployee =
                employeeService.getEmployeeById(employeeId);
        System.out.println(foundEmployee);

        System.out.println("\nGiving 10% Raise:");
        employeeService.giveRaise(employeeId, 10.0);

        System.out.println("\nUpdated Employee:");
        Employee updatedEmployee =
                employeeService.getEmployeeById(employeeId);
        System.out.println(updatedEmployee);

        System.out.println("\nAll Employees:");
        employeeService.getAllEmployees()
                .forEach(System.out::println);

        context.close();
    }
}