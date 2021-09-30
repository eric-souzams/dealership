package com.project.Dealership.controller;

import com.project.Dealership.dto.request.CarModelRequest;
import com.project.Dealership.dto.response.CarModelResponse;
import com.project.Dealership.service.CarModelService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/car-models")
public class CarModelController {

    private final CarModelService carModelService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CarModelResponse>> getAll(Pageable pageable) {
        Page<CarModelResponse> result = carModelService.findAll(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarModelResponse> getOne(@PathVariable("id") Long id) {
        CarModelResponse result = carModelService.find(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarModelResponse> create(@RequestBody @Valid CarModelRequest request) {
        CarModelResponse result = carModelService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
