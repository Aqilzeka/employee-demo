package com.whelp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeResponseModel {
    private Long id;
    private String name;
    private String surname;
    private BigDecimal salary;
}
