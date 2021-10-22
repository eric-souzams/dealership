package com.project.Dealership.mocks.Client;

import com.project.Dealership.model.entity.Client;

import java.time.LocalDate;

public class ClientMock {

    public Client newMock() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Joao Pedro Pereira");
        client.setEmail("joao@email.com");
        client.setAddress("Alguma casa ai em são paulo");
        client.setCpf("000.000.000-00");
        client.setBirthdate(LocalDate.of(1996, 10, 26));
        return client;
    }
}
