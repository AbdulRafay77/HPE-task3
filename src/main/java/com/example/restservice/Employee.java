package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;

/** A single employee, with getters and setters for JSON request/response conversion. */
public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String title;

    /** Required by Jackson when converting incoming JSON into an Employee. */
    public Employee() {
    }

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

    @JsonProperty("employee_id")
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
