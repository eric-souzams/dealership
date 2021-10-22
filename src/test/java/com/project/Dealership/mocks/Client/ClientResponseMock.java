package com.project.Dealership.mocks.Client;

import com.project.Dealership.dto.response.ClientResponse;

import java.time.LocalDate;

public class ClientResponseMock {

    public ClientResponse newMock() {
        ClientResponse response = new ClientResponse();
        response.setId(1L);
        response.setName("Joao Pedro Pereira");
        response.setEmail("joao@email.com");
        response.setAddress("Alguma casa ai em são paulo");
        response.setCpf("000.000.000-00");
        response.setBirthdate(LocalDate.of(1996, 10, 26));
        return response;
    }

}
