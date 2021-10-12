package com.project.Dealership.service;

import com.project.Dealership.dto.request.CarModelRequest;
import com.project.Dealership.dto.request.EmployeeRequest;
import com.project.Dealership.dto.response.EmployeeResponse;
import com.project.Dealership.exceptions.CarModelNotFoundException;
import com.project.Dealership.model.entity.Employee;
import com.project.Dealership.repository.EmployeeRepository;
import com.project.Dealership.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> findAll(Pageable pageable) {
        Page<Employee> result = employeeRepository.findAll(pageable);

        return result.map(EmployeeResponse::toResponse);
    }

    @Transactional(readOnly = true)
    public EmployeeResponse find(Long id) {
        Employee result = verifyIfEmployeeExist(id);

        return EmployeeResponse.toResponse(result);
    }

    @Transactional
    public EmployeeResponse save(EmployeeRequest request) {
        Employee employee = EmployeeRequest.toEntity(request);

        employee.setTotalSalesCount(0L);
        employee.setIsActive(true);
        employee = employeeRepository.save(employee);

        return EmployeeResponse.toResponse(employee);
    }

    private Employee verifyIfEmployeeExist(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CarModelNotFoundException(Messages.CAR_MODEL_NOT_FOUND));
    }

}
