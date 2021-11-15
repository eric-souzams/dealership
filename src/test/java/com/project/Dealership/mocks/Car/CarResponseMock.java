package com.project.Dealership.mocks.Car;

import com.project.Dealership.dto.response.CarResponse;
import com.project.Dealership.mocks.CarModel.CarModelResponseMock;
import com.project.Dealership.model.enums.Situation;
import com.project.Dealership.model.enums.State;

import java.util.List;

public class CarResponseMock {

    public CarResponse newMock() {
        CarResponse car = new CarResponse();

        car.setId(1L);
        car.setName("Uno Miller");
        car.setSituation(Situation.FOR_SALE.getDescription());
        car.setDescription("Carro novo em perfeito estado, apenas com alguns amassados.");
        car.setPrice(23333.99);
        car.setModel(new CarModelResponseMock().newMock());
        car.setFiles(List.of());
        car.setState(State.NEW.getDescription());

        return car;
    }

}
