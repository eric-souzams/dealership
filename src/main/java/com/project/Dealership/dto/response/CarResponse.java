package com.project.Dealership.dto.response;

import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.entity.CarModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<FileUploadResponse> files;

    public static CarResponse toResponse(Car car) {
        CarResponse response = new CarResponse();

        response.setId(car.getId());
        response.setName(car.getName());
        response.setState(car.getState().getDescription());
        response.setSituation(car.getSituation().getDescription());
        response.setDescription(car.getDescription());
        response.setModel(car.getModel());
        response.setPrice(car.getPrice());

        List<FileUploadResponse> filesList = car.getFiles().stream()
                .map(FileUploadResponse::toResponse)
                .collect(Collectors.toList());
        response.setFiles(filesList);

        return response;
    }

}
