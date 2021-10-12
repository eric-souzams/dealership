package com.project.Dealership.dto.response;

import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.entity.CarModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarResponse {

    private Long id;
    private String name;
    private String state;
    private String situation;
    private String description;
    private CarModel model;
    private Double price;
    private List<String> files;

    public static CarResponse toResponse(Car car) {
        CarResponse response = new CarResponse();

        response.setId(car.getId());
        response.setName(car.getName());
        response.setState(car.getState().getDescription());
        response.setSituation(car.getSituation().getDescription());
        response.setDescription(car.getDescription());
        response.setModel(car.getModel());
        response.setPrice(car.getPrice());
        response.setFiles(car.getFiles());

        return response;
    }

}
