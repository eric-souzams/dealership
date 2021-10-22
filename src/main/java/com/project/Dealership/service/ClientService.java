package com.project.Dealership.service;

import com.project.Dealership.dto.request.ClientRequest;
import com.project.Dealership.dto.response.ClientResponse;
import com.project.Dealership.exceptions.ClientAlreadyExistException;
import com.project.Dealership.exceptions.ClientNotFoundException;
import com.project.Dealership.model.entity.Client;
import com.project.Dealership.repository.ClientRepository;
import com.project.Dealership.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientResponse clientResponse;
    private final ClientRequest clientRequest;

    @Transactional(readOnly = true)
    public Page<ClientResponse> findAll(Pageable pageable) {
        Page<Client> result = clientRepository.findAll(pageable);

        return result.map(clientResponse::toResponse);
    }

    @Transactional(readOnly = true)
    public ClientResponse find(Long id) {
        Client result = verifyIfClientExist(id);

        return clientResponse.toResponse(result);
    }

    @Transactional
    public ClientResponse save(ClientRequest request) {
        Client client = clientRequest.toEntity(request);

        verifyIfAlreadyIsClient(request.getCpf());

        client = clientRepository.save(client);

        return clientResponse.toResponse(client);
    }

    private Client verifyIfClientExist(Long clientId) throws ClientNotFoundException {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(Messages.CLIENT_NOT_FOUND));
    }

    private void verifyIfAlreadyIsClient(String cpf) throws ClientAlreadyExistException {
        Optional<Client> result = clientRepository.findByCpf(cpf);
        if (result.isPresent()) {
            throw new ClientAlreadyExistException(Messages.CLIENT_ALREADY_EXITS);
        }
    }

}
