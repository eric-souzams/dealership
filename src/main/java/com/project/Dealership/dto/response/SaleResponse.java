package com.project.Dealership.dto.response;

import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.entity.Client;
import com.project.Dealership.model.entity.Sales;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaleResponse {

    private Long id;

    private Car car;

    private Client client;

    private LocalDateTime sold_at;

    public static SaleResponse toResponse(Sales sale) {
        SaleResponse response = new SaleResponse();

        response.setId(sale.getId());
        response.setCar(sale.getCar());
        response.setClient(sale.getClient());
        response.setSold_at(sale.getSold_at());

        return response;
    }

}
