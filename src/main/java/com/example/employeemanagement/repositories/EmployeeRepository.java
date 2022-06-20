package com.example.employeemanagement.repositories;

import com.example.employeemanagement.models.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Override
    Employee save(Employee employee);

    @Override
    Optional<Employee> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    List<Employee> findByDepartment_Id(Long id);

    @Override
    List<Employee> findAll(Sort sort);
}
