package com.project.Dealership.service;

import com.project.Dealership.dto.request.ClientRequest;
import com.project.Dealership.dto.response.ClientResponse;
import com.project.Dealership.mocks.Client.ClientMock;
import com.project.Dealership.mocks.Client.ClientRequestMock;
import com.project.Dealership.mocks.Client.ClientResponseMock;
import com.project.Dealership.model.entity.Client;
import com.project.Dealership.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}
