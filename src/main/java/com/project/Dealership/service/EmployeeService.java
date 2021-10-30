package com.project.Dealership.service;

import com.project.Dealership.dto.request.EmployeeRequest;
import com.project.Dealership.dto.response.EmployeeResponse;
import com.project.Dealership.exceptions.EmployeeAlreadyExistException;
import com.project.Dealership.exceptions.EmployeeNotFoundException;
import com.project.Dealership.model.entity.Employee;
import com.project.Dealership.repository.EmployeeRepository;
import com.project.Dealership.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeResponse employeeResponse;
    private final EmployeeRequest employeeRequest;

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> findAll(Pageable pageable) {
        Page<Employee> result = employeeRepository.findAll(pageable);

        return result.map(employeeResponse::toResponse);
    }

    @Transactional(readOnly = true)
    public EmployeeResponse find(Long id) {
        Employee result = verifyIfEmployeeExist(id);

        return employeeResponse.toResponse(result);
    }

    @Transactional
    public EmployeeResponse save(EmployeeRequest request) {
        Employee employee = employeeRequest.toEntity(request);

        verifyIfAlreadyIsEmployee(request.getCpf());

        employee.setTotalSalesCount(0L);
        employee.setIsActive(true);
        employee = employeeRepository.save(employee);

        return employeeResponse.toResponse(employee);
    }

    private Employee verifyIfEmployeeExist(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(Messages.CAR_MODEL_NOT_FOUND));
    }

    private void verifyIfAlreadyIsEmployee(String cpf) {
        Optional<Employee> result = employeeRepository.findByCpf(cpf);
        if (result.isPresent()) {
            throw new EmployeeAlreadyExistException(Messages.EMPLOYEE_ALREADY_EXITS);
        }
    }

}
