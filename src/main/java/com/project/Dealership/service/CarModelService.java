package com.project.Dealership.service;

import com.project.Dealership.dto.request.CarModelRequest;
import com.project.Dealership.dto.response.CarModelResponse;
import com.project.Dealership.exceptions.CarModelNotFoundException;
import com.project.Dealership.model.entity.CarModel;
import com.project.Dealership.repository.CarModelRepository;
import com.project.Dealership.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CarModelService {

    private final CarModelRepository carModelRepository;
    private final CarModelResponse carModelResponse;
    private final CarModelRequest carModelRequest;

    @Transactional(readOnly = true)
    public Page<CarModelResponse> findAll(Pageable pageable) {
        Page<CarModel> result = carModelRepository.findAll(pageable);

        return result.map(carModelResponse::toResponse);
    }

    @Transactional(readOnly = true)
    public CarModelResponse find(Long id) {
        CarModel result = verifyIfCarModelExist(id);

        return carModelResponse.toResponse(result);
    }

    @Transactional
    public CarModelResponse save(CarModelRequest request) {
        CarModel carModel = carModelRequest.toEntity(request);

        carModel = carModelRepository.save(carModel);

        return carModelResponse.toResponse(carModel);
    }

    private CarModel verifyIfCarModelExist(Long modelId) {
        return carModelRepository.findById(modelId)
                .orElseThrow(() -> new CarModelNotFoundException(Messages.CAR_MODEL_NOT_FOUND));
    }

}
