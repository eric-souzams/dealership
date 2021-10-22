package com.project.Dealership.mocks.Client;

import com.project.Dealership.dto.request.ClientRequest;

import java.time.LocalDate;

public class ClientRequestMock {

    public ClientRequest newMock() {
        ClientRequest request = new ClientRequest();
        request.setAddress("Alguma casa ai em são paulo");
        request.setCpf("000.000.000-00");
        request.setEmail("joao@email.com");
        request.setName("Joao Pedro Pereira");
        request.setBirthdate(LocalDate.of(1996, 10, 26));
        return request;
    }

}
