package com.project.Dealership.mocks.Sales;

import com.project.Dealership.dto.request.SaleRequest;

public class SalesRequestMock {

    public SaleRequest newMock() {
        SaleRequest request = new SaleRequest();

        request.setCarId(1L);
        request.setClientId(1L);
        request.setEmployeeId(1L);

        return request;
    }

}
