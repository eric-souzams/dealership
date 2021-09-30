package com.project.Dealership.controller;

import com.project.Dealership.dto.request.SaleRequest;
import com.project.Dealership.dto.response.SaleResponse;
import com.project.Dealership.service.SalesService;
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
@RequestMapping(value = "/sales")
public class SalesController {

    private final SalesService salesService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<SaleResponse>> getAll(Pageable pageable) {
        Page<SaleResponse> result = salesService.findAll(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleResponse> getOne(@PathVariable("id") Long id) {
        SaleResponse result = salesService.find(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleResponse> create(@RequestBody @Valid SaleRequest request) {
        SaleResponse result = salesService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
