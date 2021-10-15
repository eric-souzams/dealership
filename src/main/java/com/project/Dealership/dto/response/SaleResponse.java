package com.project.Dealership.dto.response;

import com.project.Dealership.model.entity.Sales;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaleResponse {

    private Long id;

    private CarResponse car;

    private ClientResponse client;

    private EmployeeResponse employee;

    private LocalDateTime sold_at;

    public static SaleResponse toResponse(Sales sale) {
        SaleResponse response = new SaleResponse();

        response.setId(sale.getId());
        response.setCar(CarResponse.toResponse(sale.getCar()));
        response.setClient(ClientResponse.toResponse(sale.getClient()));
        response.setEmployee(EmployeeResponse.toResponse(sale.getEmployee()));
        response.setSold_at(sale.getSold_at());

        return response;
    }

}
