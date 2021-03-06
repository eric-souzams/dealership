package com.project.Dealership.controller;

import com.project.Dealership.dto.request.CarRequest;
import com.project.Dealership.dto.response.CarResponse;
import com.project.Dealership.dto.response.FileUploadResponse;
import com.project.Dealership.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/cars")
public class CarController {

    private CarService carService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CarResponse>> getAll(Pageable pageable) {
        Page<CarResponse> result = carService.findAll(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarResponse> getOne(@PathVariable("id") Long id) {
        CarResponse result = carService.find(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarResponse> create(@RequestBody @Valid CarRequest request) {
        CarResponse result = carService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<List<FileUploadResponse>> uploadFiles(@PathVariable("id") Long carId,
                                                                @RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        List<FileUploadResponse> files = carService.uploadFiles(carId, multipartFiles);

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

}
