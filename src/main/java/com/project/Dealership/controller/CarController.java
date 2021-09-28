package com.project.Dealership.controller;

import com.project.Dealership.dto.request.CarRequest;
import com.project.Dealership.dto.response.CarResponse;
import com.project.Dealership.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<Page<CarResponse>> getAll(Pageable pageable) {
        Page<CarResponse> result = carService.findAll(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarResponse> getOne(@PathVariable("id") Long id) {
        CarResponse result = carService.find(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CarResponse> create(@RequestBody @Valid CarRequest request) {
        CarResponse result = carService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
