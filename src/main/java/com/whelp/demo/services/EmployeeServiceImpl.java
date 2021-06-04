package com.whelp.demo.services;

import com.whelp.demo.dto.EmployeeDTO;
import com.whelp.demo.entity.Employee;
import com.whelp.demo.exception.EmployeeNotFoundException;
import com.whelp.demo.mapper.EmployeeMapper;
import com.whelp.demo.mapper.PageMapping;
import com.whelp.demo.repositoty.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final EmployeeMapper MAPPER = EmployeeMapper.INSTANCE;

    private final String CALL_LOG = "Called {} method from EmployeeServiceImpl";
    private final String END_LOG = "Ended {} method from EmployeeServiceImpl";

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info(CALL_LOG, "getAllEmployees");

        Iterable<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> returnValue = MAPPER.employeeListToEmployeeDTOList(employees);

        log.info(END_LOG, "getAllEmployees");
        return returnValue;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        log.info(CALL_LOG, "getEmployeeById");

        log.info("Getting employee with id: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new EmployeeNotFoundException("Employee", id));

        EmployeeDTO returnValue = MAPPER.employeeToEmployeeDTO(employee);

        log.info(END_LOG, "getEmployeeById");
        return returnValue;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDetails) {
        log.info(CALL_LOG, "createEmployee");

        Employee employee = MAPPER.employeeDtoToEmployee(employeeDetails);
        Employee savedValue = employeeRepository.save(employee);
        EmployeeDTO returnValue = MAPPER.employeeToEmployeeDTO(savedValue);
        log.info("Saving employee: {}", returnValue);

        log.info(END_LOG, "createEmployee");
        return returnValue;
    }

    @Override
    public void updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info(CALL_LOG, "updateEmployee");

        log.info("Getting employee with id: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new EmployeeNotFoundException("Employee", id));

        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setSalary(employeeDTO.getSalary());

        employeeRepository.save(employee);

        log.info(END_LOG, "updateEmployee");
    }

    @Override
    public void deleteEmployeeById(Long id) {
        log.info(CALL_LOG, "deleteEmployee");

        log.info("Getting employee with id: {}", id);
        employeeRepository.findById(id).orElseThrow(()
                -> new EmployeeNotFoundException("Employee", id));

        employeeRepository.deleteById(id);

        log.info(END_LOG, "deleteEmployee");
    }

    @Override
    public Page<EmployeeDTO> getAllEmployeesPage(Integer pageNum, Integer pageSize,
                                                 String sortBy, String sortDir, String filter) {
        log.info(CALL_LOG, "getAllEmployeesPage");
        Pageable paging = PageRequest.of(pageNum, pageSize, ascOrDesc(sortDir, sortBy));

        Page<Employee> filteredValue = filter(filter,paging);

        Page<EmployeeDTO> returnValue = filteredValue.map(PageMapping::employeesPageToEmployeeDTOPage);

        log.info(END_LOG, "getAllEmployeesPage");
        return returnValue;
    }

    private Sort ascOrDesc(String sortDir, String sortBy) {
        // if sortDir is null return unsorted
        // if sortDir is asc return ascending
        // if sortDir is desc/other return descending

        return sortDir.equals("asc") ?
                sortBy == null ? Sort.unsorted() :
                        Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    }

    private Page<Employee> filter(String filter, Pageable pageable) {
        return filter == null ? employeeRepository.findAll(pageable) :
                employeeRepository.findByNameContainsOrSurnameContains(filter, filter, pageable);
    }

}

