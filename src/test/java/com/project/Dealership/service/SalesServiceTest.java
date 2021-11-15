package com.project.Dealership.service;

import com.project.Dealership.dto.request.SaleRequest;
import com.project.Dealership.dto.response.SaleResponse;
import com.project.Dealership.mocks.Car.CarMock;
import com.project.Dealership.mocks.Client.ClientMock;
import com.project.Dealership.mocks.Employee.EmployeeMock;
import com.project.Dealership.mocks.Sales.SalesMock;
import com.project.Dealership.mocks.Sales.SalesRequestMock;
import com.project.Dealership.mocks.Sales.SalesResponseMock;
import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.entity.Client;
import com.project.Dealership.model.entity.Employee;
import com.project.Dealership.model.entity.Sales;
import com.project.Dealership.repository.CarRepository;
import com.project.Dealership.repository.ClientRepository;
import com.project.Dealership.repository.EmployeeRepository;
import com.project.Dealership.repository.SalesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SalesServiceTest {

    @InjectMocks
    private SalesService salesService;

    @Mock
    private SalesRepository salesRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private SaleRequest saleRequest;

    @Mock
    private SaleResponse saleResponse;

    @Mock
    private Page<Sales> page;

    @Mock
    private Sales sale;

    @Test
    public void itShouldBeSaveAnNewSale() {
        //given
        Sales expectedSale = new SalesMock().newMock();
        SaleRequest expectedRequest = new SalesRequestMock().newMock();
        SaleResponse expectedResponse = new SalesResponseMock().newMock();

        Client clientReturn = new ClientMock().newMock();
        Car carReturn = new CarMock().newMock();
        Employee employeeReturn = new EmployeeMock().newMock();

        //when
        when(clientRepository.findById(expectedRequest.getClientId())).thenReturn(Optional.of(clientReturn));
        when(carRepository.findById(expectedRequest.getCarId())).thenReturn(Optional.of(carReturn));
        when(employeeRepository.findById(expectedRequest.getEmployeeId())).thenReturn(Optional.of(employeeReturn));

        when(salesRepository.save(sale)).thenReturn(expectedSale);
        when(saleResponse.toResponse(expectedSale)).thenReturn(expectedResponse);

        //then
        SaleResponse result = salesService.save(expectedRequest);

        assertNotNull(result.getCar().getId());
        assertEquals(result.getCar().getId(), carReturn.getId());
        assertEquals(result.getClient().getId(), clientReturn.getId());
        assertEquals(result.getEmployee().getId(), employeeReturn.getId());
    }
}
