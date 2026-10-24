package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getEmployeesReturnsInitialFourEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.Employees.length()").value(4))
            .andExpect(jsonPath("$.Employees[0].employee_id").value("EMP001"))
            .andExpect(jsonPath("$.Employees[3].title").value("Project Manager"));
    }

    @Test
    void postEmployeeAddsRecordVisibleViaGet() throws Exception {
        String newEmployee = """
            {
              "employee_id": "EMP005",
              "first_name": "Zain",
              "last_name": "Iqbal",
              "email": "zain.iqbal@example.com",
              "title": "DevOps Engineer"
            }
            """;

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployee))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.employee_id").value("EMP005"))
            .andExpect(jsonPath("$.first_name").value("Zain"));

        mockMvc.perform(get("/employees"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.Employees.length()").value(5))
            .andExpect(jsonPath("$.Employees[4].employee_id").value("EMP005"))
            .andExpect(jsonPath("$.Employees[4].email").value("zain.iqbal@example.com"));
    }
}
