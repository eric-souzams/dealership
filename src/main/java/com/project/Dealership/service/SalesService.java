package com.project.Dealership.service;

import com.project.Dealership.dto.request.SaleRequest;
import com.project.Dealership.dto.response.SaleResponse;
import com.project.Dealership.exceptions.CarNotFoundException;
import com.project.Dealership.exceptions.ClientNotFoundException;
import com.project.Dealership.exceptions.SaleCannotBeMadeException;
import com.project.Dealership.exceptions.SaleNotFoundException;
import com.project.Dealership.model.entity.Car;
import com.project.Dealership.model.entity.Client;
import com.project.Dealership.model.entity.Sales;
import com.project.Dealership.model.enums.Situation;
import com.project.Dealership.repository.CarRepository;
import com.project.Dealership.repository.ClientRepository;
import com.project.Dealership.repository.SalesRepository;
import com.project.Dealership.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class SalesService {

    private final SalesRepository salesRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    @Transactional(readOnly = true)
    public Page<SaleResponse> findAll(Pageable pageable) {
        Page<Sales> result = salesRepository.findAll(pageable);

        return result.map(SaleResponse::toResponse);
    }

    @Transactional(readOnly = true)
    public SaleResponse find(Long id) {
        Sales result = verifyIfSaleExist(id);

        return SaleResponse.toResponse(result);
    }

    @Transactional
    public SaleResponse save(SaleRequest request) {
        Sales sale = new Sales();

        Car car = verifyIfCarExist(request.getCarId());
        Client client = verifyIfClientExist(request.getClientId());

        verifyIfCanSellACar(car.getSituation());

        car.setSituation(Situation.SOLD);

        sale.setCar(car);
        sale.setClient(client);
        sale.setSold_at(LocalDateTime.now());

        sale = salesRepository.save(sale);

        return SaleResponse.toResponse(sale);
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

}
