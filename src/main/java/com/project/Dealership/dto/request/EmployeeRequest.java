package com.project.Dealership.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Dealership.model.entity.Employee;
import com.project.Dealership.model.enums.Profile;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @CPF
    private String cpf;

    @NotBlank
    private String address;

    @NotNull
    private Integer profile;

    public static Employee toEntity(EmployeeRequest request) {
        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setBirthdate(request.getBirthdate());
        employee.setCpf(request.getCpf());
        employee.setAddress(request.getAddress());
        employee.setProfile(Profile.values()[request.getProfile()]);

        return employee;
    }

}
