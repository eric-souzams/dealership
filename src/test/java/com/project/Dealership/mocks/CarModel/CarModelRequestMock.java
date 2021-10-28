package com.project.Dealership.mocks.CarModel;

import com.project.Dealership.dto.request.CarModelRequest;

public class CarModelRequestMock {

    public CarModelRequest newMock() {
        CarModelRequest carModel = new CarModelRequest();

        carModel.setBrand("Fiat");
        carModel.setCapacity(5);
        carModel.setFuel("Gasoline");
        carModel.setYearManufacture(2020);

        return carModel;
    }

}
