package com.moamen.repository;

import com.moamen.model.Employee;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository {

    void save(Employee employee);
    Optional<Employee> findById(UUID id);
    Collection<Employee> findAll();
}
