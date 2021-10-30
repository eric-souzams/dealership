package com.project.Dealership.dto.response;

import com.project.Dealership.model.entity.Sales;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class SaleResponse {

    private Long id;

    private CarResponse car;

    private ClientResponse client;

    private EmployeeResponse employee;

    private LocalDateTime sold_at;

    public SaleResponse toResponse(Sales sale) {
        SaleResponse response = new SaleResponse();
        ClientResponse clientResponse = new ClientResponse();
        CarResponse carResponse = new CarResponse();
        EmployeeResponse employeeResponse = new EmployeeResponse();

        response.setId(sale.getId());
        response.setCar(carResponse.toResponse(sale.getCar()));
        response.setClient(clientResponse.toResponse(sale.getClient()));
        response.setEmployee(employeeResponse.toResponse(sale.getEmployee()));
        response.setSold_at(sale.getSold_at());

        return response;
    }

}
