package com.moamen.repository;

import com.moamen.model.Employee;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
@Profile("prod")
public class FileBackedEmployeeRepository implements EmployeeRepository {

    private final Path file = Paths.get("employees.csv");

    @Override
    public void save(Employee employee) {

        String line =
                employee.getId() + "," +
                        employee.getName() + "," +
                        employee.getDepartment() + "," +
                        employee.getSalary() +
                        System.lineSeparator();

        try {
            Files.writeString(
                    file,
                    line,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to save employee",
                    e
            );
        }
    }

    @Override
    public Optional<Employee> findById(UUID id) {

        return findAll()
                .stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<Employee> findAll() {

        if (!Files.exists(file)) {
            return new ArrayList<>();
        }

        try {
            return Files.readAllLines(file)
                    .stream()
                    .map(this::mapToEmployee)
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read employees",
                    e
            );
        }
    }

    private Employee mapToEmployee(String line) {

        String[] values = line.split(",");

        return new Employee(
                UUID.fromString(values[0]),
                values[1],
                values[2],
                (float) Double.parseDouble(values[3])
        );
    }
}