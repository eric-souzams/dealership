package com.project.Dealership.controller;

import com.project.Dealership.dto.request.EmployeeRequest;
import com.project.Dealership.dto.response.EmployeeResponse;
import com.project.Dealership.service.EmployeeService;
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
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EmployeeResponse>> getAll(Pageable pageable) {
        Page<EmployeeResponse> result = employeeService.findAll(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> getOne(@PathVariable("id") Long id) {
        EmployeeResponse result = employeeService.find(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> create(@RequestBody @Valid EmployeeRequest request) {
        EmployeeResponse result = employeeService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
