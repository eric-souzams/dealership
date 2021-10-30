package com.project.Dealership.service;

import com.project.Dealership.dto.request.EmployeeRequest;
import com.project.Dealership.dto.response.EmployeeResponse;
import com.project.Dealership.exceptions.EmployeeAlreadyExistException;
import com.project.Dealership.exceptions.EmployeeNotFoundException;
import com.project.Dealership.mocks.Employee.EmployeeMock;
import com.project.Dealership.mocks.Employee.EmployeeRequestMock;
import com.project.Dealership.mocks.Employee.EmployeeResponseMock;
import com.project.Dealership.model.entity.Employee;
import com.project.Dealership.model.enums.Profile;
import com.project.Dealership.repository.EmployeeRepository;
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
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeRequest employeeRequest;

    @Mock
    private EmployeeResponse employeeResponse;

    @Mock
    private Page<Employee> page;

    @Test
    public void itShouldBeCreateAnNewEmployee() {
        //given
        Employee expectedEmployee = new EmployeeMock().newMock();
        EmployeeResponse expectedResponse = new EmployeeResponseMock().newMock();
        EmployeeRequest expectedRequest = new EmployeeRequestMock().newMock();

        //when
        when(employeeRequest.toEntity(expectedRequest)).thenReturn(expectedEmployee);
        when(employeeResponse.toResponse(expectedEmployee)).thenReturn(expectedResponse);
        when(employeeRepository.findByCpf(expectedRequest.getCpf())).thenReturn(Optional.empty());
        when(employeeRepository.save(expectedEmployee)).thenReturn(expectedEmployee);

        //then
        EmployeeResponse result = employeeService.save(expectedRequest);

        assertEquals(result.getId(), expectedEmployee.getId());
        assertEquals(result.getIsActive(), true);
        assertEquals(result.getProfile(), Profile.SALESMAN.getDescription());
        assertEquals(result.getTotalSalesCount(), 0L);
    }

    @Test
    public void itShouldBeThrowAnExceptionWhenEmployeeAlreadyRegistered() {
        //given
        Employee expectedEmployee = new EmployeeMock().newMock();
        EmployeeRequest expectedRequest = new EmployeeRequestMock().newMock();

        //given
        when(employeeRepository.findByCpf(expectedRequest.getCpf())).thenReturn(Optional.of(expectedEmployee));

        //then
        assertThrows(EmployeeAlreadyExistException.class, () -> employeeService.save(expectedRequest));
    }

    @Test
    public void itShouldBeFindAnEmployee() {
        //given
        Long informedId = 1L;
        Employee expectedEmployee = new EmployeeMock().newMock();
        EmployeeResponse expectedResponse = new EmployeeResponseMock().newMock();

        //when
        when(employeeRepository.findById(informedId)).thenReturn(Optional.of(expectedEmployee));
        when(employeeResponse.toResponse(expectedEmployee)).thenReturn(expectedResponse);

        //then
        EmployeeResponse result = employeeService.find(informedId);

        assertEquals(result.getId(), expectedEmployee.getId());
        assertEquals(result.getProfile(), Profile.SALESMAN.getDescription());
    }

    @Test
    public void itShouldBeThrowAnExceptionWhenEmployeeNotFound() {
        //given
        Long informedId = 1L;

        //when
        when(employeeRepository.findById(informedId)).thenReturn(Optional.empty());

        //then
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find(informedId));
    }

    @Test
    public void itShouldBeFindAllEmployees() {
        //given
        Employee expectedEmployee = new EmployeeMock().newMock();
        EmployeeResponse expectedResponse = new EmployeeResponseMock().newMock();

        Page<Employee> expectedEmployeePage = new PageImpl<>(List.of(expectedEmployee));
        Page<EmployeeResponse> expectedResponsePage = new PageImpl<>(List.of(expectedResponse));

        //when
        when(employeeRepository.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(expectedEmployeePage);
        when(employeeResponse.toResponse(expectedEmployee)).thenReturn(expectedResponse);
        when(page.map(employeeResponse::toResponse)).thenReturn(expectedResponsePage);

        //then
        Page<EmployeeResponse> foundEmployeesList = employeeService.findAll(PageRequest.of(1, 1));

        assertEquals(foundEmployeesList.toList().get(0).getId(), expectedEmployee.getId());
        assertEquals(foundEmployeesList.toList().get(0).getProfile(), expectedEmployee.getProfile().getDescription());
        assertEquals(foundEmployeesList.toList().get(0).getIsActive(), expectedEmployee.getIsActive());
    }
}
