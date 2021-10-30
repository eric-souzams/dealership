package com.project.Dealership.service;

import com.project.Dealership.dto.request.CarRequest;
import com.project.Dealership.dto.response.CarResponse;
import com.project.Dealership.dto.response.FileUploadResponse;
import com.project.Dealership.exceptions.CarModelNotFoundException;
import com.project.Dealership.exceptions.CarNotFoundException;
import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.entity.CarModel;
import com.project.Dealership.model.entity.FileUpload;
import com.project.Dealership.model.enums.Situation;
import com.project.Dealership.repository.CarModelRepository;
import com.project.Dealership.repository.CarRepository;
import com.project.Dealership.repository.FileUploadRepository;
import com.project.Dealership.utils.Messages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarModelRepository carModelRepository;
    private final FileUploadRepository fileUploadRepository;
    private final CarResponse carResponse;
    private final CarRequest carRequest;

    @Value("${file.upload.directory}")
    private String DIRECTORY;

    public CarService(CarRepository carRepository, CarModelRepository carModelRepository, FileUploadRepository fileUploadRepository, CarResponse carResponse, CarRequest carRequest) {
        this.carRepository = carRepository;
        this.carModelRepository = carModelRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.carResponse = carResponse;
        this.carRequest = carRequest;
    }

    @Transactional(readOnly = true)
    public Page<CarResponse> findAll(Pageable pageable) {
        Page<Car> result = carRepository.findAll(pageable);

        return result.map(carResponse::toResponse);
    }

    @Transactional(readOnly = true)
    public CarResponse find(Long id) {
        Car car = verifyIfCarExist(id);

        return carResponse.toResponse(car);
    }

    @Transactional
    public CarResponse save(CarRequest request) {
        Car car = carRequest.toEntity(request);

        car.setSituation(Situation.FOR_SALE);

        CarModel carModel = verifyIfCarModelExist(request.getModelId());
        car.setModel(carModel);

        car = carRepository.save(car);

        return carResponse.toResponse(car);
    }

    @Transactional
    public List<FileUploadResponse> uploadFiles(Long carId, List<MultipartFile> files) throws IOException {
        Car car = verifyIfCarExist(carId);

        List<FileUpload> filesUpload = new ArrayList<>();

        for (MultipartFile file : files) {
            String filename = System.currentTimeMillis() + "-" + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);

            FileUpload newFile = new FileUpload();
            newFile.setId(UUID.randomUUID());
            newFile.setPath(filename);
            newFile.setCreated_at(LocalDateTime.now());

            newFile = fileUploadRepository.save(newFile);

            filesUpload.add(newFile);
        }

        car.addFiles(filesUpload);

        return filesUpload.stream().map(FileUploadResponse::toResponse).collect(Collectors.toList());
    }

    private Car verifyIfCarExist(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(Messages.CAR_NOT_FOUND));
    }

    private CarModel verifyIfCarModelExist(Long modelId) {
        return carModelRepository.findById(modelId)
                .orElseThrow(() -> new CarModelNotFoundException(Messages.CAR_MODEL_NOT_FOUND));
    }

}
