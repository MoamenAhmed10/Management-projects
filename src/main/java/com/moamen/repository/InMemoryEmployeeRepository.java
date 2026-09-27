package com.moamen.repository;

import com.moamen.model.Employee;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
@Repository
@Profile("dev")

public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final Map<UUID, Employee> EmployeesDb = new ConcurrentHashMap<>();

    @Override
    public void save(Employee employee) {
        EmployeesDb.put(employee.getId(), employee);

    }

    @Override
    public Optional<Employee> findById(UUID id) {
        return Optional.ofNullable(EmployeesDb.get(id));

    }

    @Override
    public Collection<Employee> findAll() {
        return EmployeesDb.values();

    }

}
