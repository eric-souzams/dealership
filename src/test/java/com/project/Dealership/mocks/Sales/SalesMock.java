package com.project.Dealership.mocks.Sales;

import com.project.Dealership.mocks.Car.CarMock;
import com.project.Dealership.mocks.Client.ClientMock;
import com.project.Dealership.mocks.Employee.EmployeeMock;
import com.project.Dealership.model.entity.Sales;

import java.time.LocalDateTime;

public class SalesMock {

    public Sales newMock() {
        Sales sales = new Sales();

        sales.setId(1L);
        sales.setCar(new CarMock().newMock());
        sales.setClient(new ClientMock().newMock());
        sales.setEmployee(new EmployeeMock().newMock());
        sales.setSold_at(LocalDateTime.of(2021, 8, 24, 16, 30));

        return sales;
    }

}
