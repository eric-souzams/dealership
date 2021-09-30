package com.project.Dealership.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Dealership.model.entity.Client;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientResponse {

    private Long id;

    private String name;

    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    private String cpf;

    private String address;

    public static ClientResponse toResponse(Client client) {
        ClientResponse response = new ClientResponse();

        response.setId(client.getId());
        response.setName(client.getName());
        response.setEmail(client.getEmail());
        response.setBirthdate(client.getBirthdate());
        response.setCpf(client.getCpf());
        response.setAddress(client.getAddress());

        return response;
    }
}
