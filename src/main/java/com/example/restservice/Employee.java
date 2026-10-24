package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Represents a single employee. This task requires read-only fields. */
public class Employee {
    private final String employeeId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String title;

    public Employee(String employeeId, String firstName, String lastName,
                    String email, String title) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
    }

    @JsonProperty("employee_id")
    public String getEmployeeId() {
        return employeeId;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }
}
