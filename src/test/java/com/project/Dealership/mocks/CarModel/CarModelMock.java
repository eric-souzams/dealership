package com.project.Dealership.mocks.CarModel;

import com.project.Dealership.model.entity.CarModel;

public class CarModelMock {

    public CarModel newMock() {
        CarModel carModel = new CarModel();

        carModel.setId(1L);
        carModel.setFuel("Gasoline");
        carModel.setYearManufacture(2020);
        carModel.setBrand("Fiat");
        carModel.setCapacity(5);

        return carModel;
    }
}
