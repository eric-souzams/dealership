package com.project.Dealership.mocks.Sales;

import com.project.Dealership.dto.response.SaleResponse;
import com.project.Dealership.mocks.Car.CarResponseMock;
import com.project.Dealership.mocks.Client.ClientResponseMock;
import com.project.Dealership.mocks.Employee.EmployeeResponseMock;

import java.time.LocalDateTime;

public class SalesResponseMock {

    public SaleResponse newMock() {
        SaleResponse response = new SaleResponse();

        response.setId(1L);
        response.setCar(new CarResponseMock().newMock());
        response.setClient(new ClientResponseMock().newMock());
        response.setEmployee(new EmployeeResponseMock().newMock());
        response.setSold_at(LocalDateTime.of(2021, 8, 24, 16, 30));

        return response;
    }

}
