package com.moamen.service;


import com.moamen.exception.EmployeeException;
import com.moamen.model.Employee;
import com.moamen.notify.Notifier;
import com.moamen.repository.EmployeeRepository;
import com.moamen.utiles.validators.EmployeeValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

//import static sun.security.util.KeyUtil.validate;

//import static com.moamen.utiles.validators.EmployeeValidator.validate;

@Service
public class EmployeeServiceImpl implements EmployeeService {

private final EmployeeRepository employeeRepository;
private final EmployeeValidator employeeValidator;
private final Notifier notifier;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeValidator employeeValidator, @Qualifier("emailNotifier") Notifier notifier) {
        this.employeeRepository = employeeRepository;
        this.employeeValidator = employeeValidator;
        this.notifier = notifier;
    }


    @Override
    public void addEmployee(Employee employee)throws EmployeeException {
        employeeValidator.validate(employee);

        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());

        if (existingEmployee.isPresent()) {
            throw new EmployeeException("Employee already exists");
        }

        employeeRepository.save(employee);

        notifier.send("New employee added: " + employee.getName());

    }

    @Override
    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeException("Employee not found"));
    }

    @Override
    public Collection<Employee> getAllEmployees() {
      final Collection<Employee> employees = employeeRepository.findAll();

      if (employees.isEmpty()) {
          throw new EmployeeException("Employees not found");
      }
       return employees;

    }

    @Override
    public void giveRaise(UUID id, Double percentage) {
        Employee employee= employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeException("Employee not found"));

        if (percentage <= 0 ) {
            throw new EmployeeException("Percentage must be greater than 0");
        }

        employee.setBonus(percentage);

        employeeRepository.save(employee);

    }
}
