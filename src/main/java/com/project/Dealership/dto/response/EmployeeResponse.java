package com.project.Dealership.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Dealership.model.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Component
public class EmployeeResponse {

    private Long id;

    private String name;

    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    private String cpf;

    private String address;

    private String profile;

    private Long totalSalesCount;

    private Boolean isActive;

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse result = new EmployeeResponse();

        result.setId(employee.getId());
        result.setName(employee.getName());
        result.setEmail(employee.getEmail());
        result.setBirthdate(employee.getBirthdate());
        result.setCpf(employee.getCpf());
        result.setAddress(employee.getAddress());
        result.setProfile(employee.getProfile().getDescription());
        result.setTotalSalesCount(employee.getTotalSalesCount());
        result.setIsActive(employee.getIsActive());

        return result;
    }

}
