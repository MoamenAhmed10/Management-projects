package com.moamen.utiles.validators;

import com.moamen.exception.EmployeeException;
import com.moamen.model.Employee;
import org.springframework.stereotype.Component;

@Component
public  class EmployeeValidator {

    public void validate(Employee employee)
            throws EmployeeException {

        if (employee == null) {
            throw new EmployeeException("Employee is null");
        }

        if (employee.getId() == null) {
            throw new EmployeeException("Employee ID is required");
        }

        if (employee.getName() == null ||
                employee.getName().isBlank()) {

            throw new EmployeeException(
                    "Employee name is required"
            );
        }

        if (employee.getSalary() < 0) {
            throw new EmployeeException(
                    "Employee salary cannot be negative"
            );
        }
    }
}