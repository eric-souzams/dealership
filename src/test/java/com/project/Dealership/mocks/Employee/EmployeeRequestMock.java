package com.project.Dealership.mocks.Employee;

import com.project.Dealership.dto.request.EmployeeRequest;

import java.time.LocalDate;

public class EmployeeRequestMock {

    public EmployeeRequest newMock() {
        EmployeeRequest employee = new EmployeeRequest();

        employee.setName("Marcos Lucas Soares");
        employee.setEmail("marcos@email.com");
        employee.setBirthdate(LocalDate.of(1988, 7, 10));
        employee.setCpf("000.000.001-00");
        employee.setAddress("Moro em uma casa na bahia");
        employee.setProfile(2);

        return employee;
    }

}
