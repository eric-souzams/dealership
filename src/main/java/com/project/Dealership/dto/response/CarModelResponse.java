package com.project.Dealership.dto.response;

import com.project.Dealership.model.entity.CarModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CarModelResponse {

    private Long id;

    private String fuel;

    private Integer yearManufacture;

    private String brand;

    private Integer capacity;

    public CarModelResponse toResponse(CarModel carModel) {
        CarModelResponse response = new CarModelResponse();

        response.setId(carModel.getId());
        response.setFuel(carModel.getFuel());
        response.setYearManufacture(carModel.getYearManufacture());
        response.setBrand(carModel.getBrand());
        response.setCapacity(carModel.getCapacity());

        return response;
    }

}
