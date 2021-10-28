package com.project.Dealership.service;

import com.project.Dealership.dto.request.ClientRequest;
import com.project.Dealership.dto.response.ClientResponse;
import com.project.Dealership.exceptions.ClientAlreadyExistException;
import com.project.Dealership.exceptions.ClientNotFoundException;
import com.project.Dealership.mocks.Client.ClientMock;
import com.project.Dealership.mocks.Client.ClientRequestMock;
import com.project.Dealership.mocks.Client.ClientResponseMock;
import com.project.Dealership.model.entity.Client;
import com.project.Dealership.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ClientMockServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientRequest clientRequest;

    @Mock
    private ClientResponse clientResponse;

    @Mock
    private Page<Client> page;

    @Test
    public void itShouldBeCreateAnNewClient() {
        //given
        Client expectedClient = new ClientMock().newMock();
        ClientRequest expectedRequest = new ClientRequestMock().newMock();
        ClientResponse expectedResponse = new ClientResponseMock().newMock();

        //when
        when(clientRepository.findByCpf(expectedClient.getCpf())).thenReturn(Optional.empty());
        when(clientRepository.save(expectedClient)).thenReturn(expectedClient);
        when(clientRequest.toEntity(expectedRequest)).thenReturn(expectedClient);
        when(clientResponse.toResponse(expectedClient)).thenReturn(expectedResponse);

        //then
        ClientResponse createdClient = clientService.save(expectedRequest);

        assertEquals(createdClient.getId(), expectedClient.getId());
        assertEquals(createdClient.getCpf(), expectedClient.getCpf());
        assertEquals(createdClient.getName(), expectedClient.getName());
    }

    @Test
    public void itShouldBeThrowAnExceptionWhenClientAlreadyRegistered() {
        //given
        ClientRequest expectedRequest = new ClientRequestMock().newMock();
        Client expectedClient = new ClientMock().newMock();

        //when
        when(clientRepository.findByCpf(expectedRequest.getCpf())).thenReturn(Optional.of(expectedClient));

        //then
        assertThrows(ClientAlreadyExistException.class, () -> clientService.save(expectedRequest));
    }

    @Test
    public void itShouldBeFindClientById() {
        //given
        Long informedId = 1L;
        Client expectedClient = new ClientMock().newMock();
        ClientResponse expectedResponse = new ClientResponseMock().newMock();

        //when
        when(clientRepository.findById(informedId)).thenReturn(Optional.of(expectedClient));
        when(clientResponse.toResponse(expectedClient)).thenReturn(expectedResponse);

        //then
        ClientResponse foundClient = clientService.find(expectedClient.getId());

        assertEquals(informedId, foundClient.getId());
    }

    @Test
    public void itShouldBeThrowAnExceptionWhenClientNotFound() {
        //given
        Client expectedClient = new ClientMock().newMock();

        //when
        when(clientRepository.findById(expectedClient.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(ClientNotFoundException.class, () -> clientService.find(expectedClient.getId()));
    }

    @Test
    public void itShouldBeFindAllClients() {
        //given
        Client expectedClient = new ClientMock().newMock();
        ClientResponse expectedResponse = new ClientResponseMock().newMock();

        Page<Client> expectedClientPage = new PageImpl<>(List.of(expectedClient));
        Page<ClientResponse> expectedClientResponsePage = new PageImpl<>(List.of(expectedResponse));

        //when
        when(clientRepository.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(expectedClientPage);
        when(clientResponse.toResponse(expectedClient)).thenReturn(expectedResponse);
        when(page.map(clientResponse::toResponse)).thenReturn(expectedClientResponsePage);

        //then
        Page<ClientResponse> foundClientsList = clientService.findAll(PageRequest.of(1, 1));

        assertEquals(foundClientsList.toList().get(0).getId(), expectedClient.getId());
        assertEquals(foundClientsList.toList().get(0).getName(), expectedClient.getName());
    }
}
