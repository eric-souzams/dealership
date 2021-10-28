package com.project.Dealership.mocks.CarModel;

import com.project.Dealership.dto.response.CarModelResponse;

public class CarModelResponseMock {

    public CarModelResponse newMock() {
        CarModelResponse carModel = new CarModelResponse();

        carModel.setId(1L);
        carModel.setFuel("Gasoline");
        carModel.setYearManufacture(2020);
        carModel.setBrand("Fiat");
        carModel.setCapacity(5);

        return carModel;
    }

}
