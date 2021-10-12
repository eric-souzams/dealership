package com.project.Dealership.service;

import com.project.Dealership.dto.request.ClientRequest;
import com.project.Dealership.dto.response.ClientResponse;
import com.project.Dealership.exceptions.ClientAlreadyExistException;
import com.project.Dealership.exceptions.ClientNotFoundException;
import com.project.Dealership.model.entity.Client;
import com.project.Dealership.repository.ClientRepository;
import com.project.Dealership.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientResponse> findAll(Pageable pageable) {
        Page<Client> result = clientRepository.findAll(pageable);

        return result.map(ClientResponse::toResponse);
    }

    @Transactional(readOnly = true)
    public ClientResponse find(Long id) {
        Client result = verifyIfClientExist(id);

        return ClientResponse.toResponse(result);
    }

    @Transactional
    public ClientResponse save(ClientRequest request) {
        Client client = ClientRequest.toEntity(request);

        verifyIfAlreadyIsClient(request.getCpf());

        client = clientRepository.save(client);

        return ClientResponse.toResponse(client);
    }

    private Client verifyIfClientExist(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(Messages.CLIENT_NOT_FOUND));
    }

    private void verifyIfAlreadyIsClient(String cpf) {
        Optional<Client> result = clientRepository.findByCpf(cpf);
        if (result.isPresent()) {
            throw new ClientAlreadyExistException(Messages.CLIENT_ALREADY_EXITS);
        }
    }

}
