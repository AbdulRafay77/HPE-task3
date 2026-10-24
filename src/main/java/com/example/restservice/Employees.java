package com.example.restservice;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Wrapper containing the complete employee list. */
public class Employees {
    private List<Employee> employees = List.of();

    // The exercise shows a capital E in the response's top-level key.
    @JsonProperty("Employees")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        // Take a defensive snapshot; the GET endpoint cannot modify records.
        this.employees = List.copyOf(employees);
    }
}
