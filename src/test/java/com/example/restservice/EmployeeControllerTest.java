package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getEmployeesReturnsJsonWithFourEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.Employees.length()").value(4))
            .andExpect(jsonPath("$.Employees[0].employee_id").value("EMP001"))
            .andExpect(jsonPath("$.Employees[0].first_name").value("Ali"))
            .andExpect(jsonPath("$.Employees[3].title").value("Project Manager"));
    }
}
