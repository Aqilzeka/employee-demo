package com.whelp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequestModel {

    @NotNull(message = "First name cannot be null")
    @Size(min = 3, max = 50, message = "Name must be equal or greater than 3 characters and less than 50 characters")
    private String name;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 3, max = 50, message = "Surname must be equal or greater than 3 characters and less than 50 characters")
    private String surname;

    @NotNull(message = "Password cannot be null")
    @Positive(message = "Salary must be positive value")
    @DecimalMin(value = "0.0", message = "Salary must be equal or greater than 0.0")
    private BigDecimal salary;
}
