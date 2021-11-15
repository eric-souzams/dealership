package com.project.Dealership.mocks.Car;

import com.project.Dealership.dto.request.CarRequest;

public class CarRequestMock {

    public CarRequest newMock() {
        CarRequest car = new CarRequest();

        car.setName("Uno Miller");
        car.setDescription("Carro novo em perfeito estado, apenas com alguns amassados.");
        car.setPrice(23333.99);
        car.setState(0);
        car.setModelId(1L);

        return car;
    }

}
