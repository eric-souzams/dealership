package com.project.Dealership.service;

import com.project.Dealership.dto.request.SaleRequest;
import com.project.Dealership.dto.response.SaleResponse;
import com.project.Dealership.exceptions.*;
import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.entity.Client;
import com.project.Dealership.model.entity.Employee;
import com.project.Dealership.model.entity.Sales;
import com.project.Dealership.model.enums.Situation;
import com.project.Dealership.repository.CarRepository;
import com.project.Dealership.repository.ClientRepository;
import com.project.Dealership.repository.EmployeeRepository;
import com.project.Dealership.repository.SalesRepository;
import com.project.Dealership.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class SalesService {

    private final SalesRepository salesRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;
    private final SaleResponse saleResponse;
    private final SaleRequest saleRequest;

    @Transactional(readOnly = true)
    public Page<SaleResponse> findAll(Pageable pageable) {
        Page<Sales> result = salesRepository.findAll(pageable);

        return result.map(saleResponse::toResponse);
    }

    @Transactional(readOnly = true)
    public SaleResponse find(Long id) {
        Sales result = verifyIfSaleExist(id);

        return saleResponse.toResponse(result);
    }

    @Transactional
    public SaleResponse save(SaleRequest request) {
        Sales sale = new Sales();

        Car car = verifyIfCarExist(request.getCarId());
        Client client = verifyIfClientExist(request.getClientId());
        Employee employee = verifyIfEmployeeExist(request.getEmployeeId());

        verifyIfCanSellACar(car.getSituation());

        car.setSituation(Situation.SOLD);

        sale.setCar(car);
        sale.setClient(client);
        sale.setEmployee(employee);
        sale.setSold_at(LocalDateTime.now());

        sale = salesRepository.save(sale);

        client.addCar(car);

        employee.incrementTotalSales();

        return saleResponse.toResponse(sale);
    }

    private Sales verifyIfSaleExist(Long saleId) {
        return salesRepository.findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException(Messages.SALE_NOT_FOUND));
    }

    private Client verifyIfClientExist(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(Messages.CLIENT_NOT_FOUND));
    }

    private Car verifyIfCarExist(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(Messages.CAR_NOT_FOUND));
    }

    private void verifyIfCanSellACar(Situation situation) {
        if (situation == Situation.SOLD) {
            throw new SaleCannotBeMadeException(Messages.SALE_CANNOT_BE_MADE);
        }
    }

    private Employee verifyIfEmployeeExist(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(Messages.EMPLOYEE_NOT_FOUND));
    }

}
