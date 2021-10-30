package com.project.Dealership.mocks.Employee;

import com.project.Dealership.model.entity.Employee;
import com.project.Dealership.model.enums.Profile;

import java.time.LocalDate;

public class EmployeeMock {

    public Employee newMock() {
        Employee employee = new Employee();

        employee.setId(1L);
        employee.setName("Marcos Lucas Soares");
        employee.setEmail("marcos@email.com");
        employee.setBirthdate(LocalDate.of(1988, 7, 10));
        employee.setCpf("000.000.001-00");
        employee.setAddress("Moro em uma casa na bahia");
        employee.setProfile(Profile.SALESMAN);
        employee.setTotalSalesCount(0L);
        employee.setIsActive(true);

        return employee;
    }

}
