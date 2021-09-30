package com.project.Dealership.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Dealership.model.entity.Client;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ClientRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @CPF
    private String cpf;

    @NotBlank
    private String address;

    public static Client toEntity(ClientRequest request) {
        Client client = new Client();

        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setBirthdate(request.getBirthdate());
        client.setCpf(request.getCpf());
        client.setAddress(request.getAddress());

        return client;
    }

}
