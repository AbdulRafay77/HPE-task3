package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/** Keeps employee records in memory for the duration of the running application. */
@Component
public class EmployeeManager {
    private final Employees employees = new Employees();

    public EmployeeManager() {
        employees.setEmployees(List.of(
            new Employee("EMP001", "Ali", "Khan", "ali.khan@example.com", "Software Engineer"),
            new Employee("EMP002", "Sara", "Ahmed", "sara.ahmed@example.com", "Backend Developer"),
            new Employee("EMP003", "Hassan", "Raza", "hassan.raza@example.com", "QA Engineer"),
            new Employee("EMP004", "Ayesha", "Malik", "ayesha.malik@example.com", "Project Manager")
        ));
    }

    public synchronized Employees getEmployees() {
        return employees;
    }

    /** Adds a new employee to an updated snapshot of the in-memory list. */
    public synchronized Employee addEmployee(Employee employee) {
        List<Employee> updated = new ArrayList<>(employees.getEmployees());
        updated.add(employee);
        employees.setEmployees(updated);
        return employee;
    }
}
