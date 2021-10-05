package com.project.Dealership.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaleRequest {

    @NotNull
    private Long carId;

    @NotNull
    private Long clientId;

    @NotNull
    private Long employeeId;

}
