package com.example.restservice;

import java.util.List;
import org.springframework.stereotype.Component;

/** Initializes and holds 4 hard-coded employees (no database for Task Two). */
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

    public Employees getEmployees() {
        return employees;
    }
}
