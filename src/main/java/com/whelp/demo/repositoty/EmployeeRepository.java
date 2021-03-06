package com.whelp.demo.repositoty;

import com.whelp.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    PageImpl<Employee> findByNameContainsOrSurnameContains(String name, String surname, Pageable pageable);

}
