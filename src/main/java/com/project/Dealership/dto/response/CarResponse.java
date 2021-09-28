package com.project.Dealership.dto.response;

import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.entity.CarModel;
import com.project.Dealership.model.enums.Situation;
import com.project.Dealership.model.enums.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponse {

    private Long id;
    private String name;
    private State state;
    private Situation situation;
    private String description;
    private CarModel model;
    private Double price;

    public static CarResponse toResponse(Car car) {
        CarResponse response = new CarResponse();

        response.setId(car.getId());
        response.setName(car.getName());
        response.setState(car.getState());
        response.setSituation(car.getSituation());
        response.setDescription(car.getDescription());
        response.setModel(car.getModel());
        response.setPrice(car.getPrice());

        return response;
    }

}
