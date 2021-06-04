package com.whelp.demo.mapper;

import com.whelp.demo.dto.EmployeeDTO;
import com.whelp.demo.entity.Employee;
import com.whelp.demo.model.CreateEmployeeRequestModel;
import com.whelp.demo.model.CreateEmployeeResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Employee employeeDtoToEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO createEmployeeRequestModelToEmployeeDTO(CreateEmployeeRequestModel employeeDetails);

    List<EmployeeDTO> employeeListToEmployeeDTOList(Iterable<Employee> employeeDTO);

    CreateEmployeeResponseModel employeeToCreateEmployeeResponseModel(EmployeeDTO employeeDTO);

    List<CreateEmployeeResponseModel> employeesDTOListToCreateEmployeeResponseModelList(List<EmployeeDTO> employeesDTOList);

    List<Employee> employeesDTOListToEmployeeList(List<EmployeeDTO> allEmployees);
}
