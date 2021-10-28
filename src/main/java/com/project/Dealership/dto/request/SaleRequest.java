package com.project.Dealership.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Component
public class SaleRequest {

    @NotNull
    private Long carId;

    @NotNull
    private Long clientId;

    @NotNull
    private Long employeeId;

}
