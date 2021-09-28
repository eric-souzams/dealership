package com.project.Dealership.service;

import com.project.Dealership.dto.request.CarRequest;
import com.project.Dealership.dto.response.CarResponse;
import com.project.Dealership.exceptions.CarModelNotFoundException;
import com.project.Dealership.exceptions.CarNotFoundException;
import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.entity.CarModel;
import com.project.Dealership.model.enums.Situation;
import com.project.Dealership.repository.CarModelRepository;
import com.project.Dealership.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarModelRepository carModelRepository;

    @Transactional(readOnly = true)
    public Page<CarResponse> findAll(Pageable pageable) {
        Page<Car> result = carRepository.findAll(pageable);

        return result.map(CarResponse::toResponse);
    }

    @Transactional(readOnly = true)
    public CarResponse find(Long id) {
        Car car = verifyIfCarExist(id);

        return CarResponse.toResponse(car);
    }

    @Transactional
    public CarResponse save(CarRequest request) {
        Car car = CarRequest.toEntity(request);

        car.setSituation(Situation.FOR_SALE);

        CarModel carModel = verifyIfCarModelExist(request.getModelId());
        car.setModel(carModel);

        car = carRepository.save(car);

        return CarResponse.toResponse(car);
    }

    private Car verifyIfCarExist(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car not has found."));
    }

    private CarModel verifyIfCarModelExist(Long modelId) {
        return carModelRepository.findById(modelId)
                .orElseThrow(() -> new CarModelNotFoundException("Car model not has found."));
    }

}
