package com.project.Dealership.controller;

import com.project.Dealership.dto.request.ClientRequest;
import com.project.Dealership.dto.response.ClientResponse;
import com.project.Dealership.service.ClientService;
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
@RequestMapping(value = "/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClientResponse>> getAll(Pageable pageable) {
        Page<ClientResponse> result = clientService.findAll(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponse> getOne(@PathVariable("id") Long id) {
        ClientResponse result = clientService.find(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponse> create(@RequestBody @Valid ClientRequest request) {
        ClientResponse result = clientService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
