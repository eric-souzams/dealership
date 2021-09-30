package com.project.Dealership.dto.request;

import com.project.Dealership.model.entity.CarModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CarModelRequest {

    @NotBlank
    private String fuel;

    @NotNull
    @Min(1950)
    private Integer yearManufacture;

    @NotBlank
    private String brand;

    @NotNull
    @Min(1)
    private Integer capacity;

    public static CarModel toEntity(CarModelRequest request) {
        CarModel carModel = new CarModel();

        carModel.setFuel(request.getFuel());
        carModel.setYearManufacture(request.getYearManufacture());
        carModel.setBrand(request.getBrand());
        carModel.setCapacity(request.getCapacity());

        return carModel;
    }

}
