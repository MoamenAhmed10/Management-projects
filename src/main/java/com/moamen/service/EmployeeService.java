package com.moamen.service;

import com.moamen.model.Employee;

import java.util.Collection;
import java.util.UUID;

public interface EmployeeService {

   void addEmployee(Employee employee) ;

   Employee getEmployeeById(UUID id);

   Collection<Employee > getAllEmployees();

   void giveRaise(UUID id,Double percentage);

}
