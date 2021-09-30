package com.project.Dealership.dto.request;

import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.enums.State;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CarRequest {

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    @Max(2)
    private Integer state;

    @NotBlank
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Long modelId;

    public static Car toEntity(CarRequest request) {
        Car car = new Car();

        car.setName(request.getName());
        car.setState(State.values()[request.getState()]);
        car.setDescription(request.getDescription());
        car.setPrice(request.getPrice());

        return car;
    }

}
