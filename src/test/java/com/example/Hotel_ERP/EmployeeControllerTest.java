package com.example.Hotel_ERP;

import com.example.Hotel_ERP.Identity.Employee.Employee;
import com.example.Hotel_ERP.Identity.Employee.EmployeeController;
import com.example.Hotel_ERP.Identity.Employee.EmployeeDto;
import com.example.Hotel_ERP.Identity.Employee.EmployeeService;
import com.example.Hotel_ERP.Shared.Config.JwtAuthFilter;
import com.example.Hotel_ERP.Shared.Config.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtHelper jwtHelper;

    @MockitoBean
    private EmployeeService employeeService;

    @MockitoBean
    private JwtAuthFilter jwtAuthFilter;

    @Test
    void shouldReturnAllEmployees () throws Exception {
        // Arrange
        List<Employee> employees = List.of(
                Employee.createEmployee(new EmployeeDto.CreateEmployee("Moe", "Moe", "moe@", LocalDate.now(), LocalDate.now(), "20$", "manager")),
                Employee.createEmployee(new EmployeeDto.CreateEmployee("Emma", "Emma", "emma@", LocalDate.now(), LocalDate.now(), "20$", "manager")),
                Employee.createEmployee(new EmployeeDto.CreateEmployee("joe", "joe", "joe@", LocalDate.now(), LocalDate.now(), "20$", "manager"))
        );

        // Display some data
        for (int x = 0; employees.size() > x; x++) {
            System.out.println(employees.get(x).getEmail());
        }
        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Assert
        mockMvc
                .perform(get("/employee"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
