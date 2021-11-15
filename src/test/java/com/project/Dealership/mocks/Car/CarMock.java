package com.project.Dealership.mocks.Car;

import com.project.Dealership.mocks.CarModel.CarModelMock;
import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.enums.Situation;
import com.project.Dealership.model.enums.State;

import java.util.List;

public class CarMock {

    public Car newMock() {
        Car car = new Car();

        car.setId(1L);
        car.setName("Uno Miller");
        car.setSituation(Situation.FOR_SALE);
        car.setDescription("Carro novo em perfeito estado, apenas com alguns amassados.");
        car.setPrice(23333.99);
        car.setModel(new CarModelMock().newMock());
        car.setFiles(List.of());
        car.setState(State.NEW);

        return car;
    }

}
