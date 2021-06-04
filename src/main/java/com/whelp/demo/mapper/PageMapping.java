package com.whelp.demo.mapper;

import com.whelp.demo.dto.EmployeeDTO;
import com.whelp.demo.entity.Employee;
import com.whelp.demo.model.CreateEmployeeResponseModel;

public interface PageMapping {

    static CreateEmployeeResponseModel employeesDTOPageToCreateEmployeeResponseModel(EmployeeDTO employeeDTO) {

        CreateEmployeeResponseModel createEmployeeResponseModel = new CreateEmployeeResponseModel();
        createEmployeeResponseModel.setId(employeeDTO.getId());
        createEmployeeResponseModel.setName(employeeDTO.getName());
        createEmployeeResponseModel.setSurname(employeeDTO.getSurname());
        createEmployeeResponseModel.setSalary(employeeDTO.getSalary());

        return createEmployeeResponseModel;
    }

    static EmployeeDTO employeesPageToEmployeeDTOPage(Employee employee) {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSurname(employee.getSurname());
        employeeDTO.setSalary(employee.getSalary());

        return employeeDTO;
    }

    static Employee employeesDTOPageToEmployeePage(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setSalary(employeeDTO.getSalary());
        return employee;
    }
}
