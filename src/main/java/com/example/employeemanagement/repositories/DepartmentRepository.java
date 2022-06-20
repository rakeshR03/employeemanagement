package com.example.employeemanagement.repositories;

import com.example.employeemanagement.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Override
    Department save(Department department);

    @Override
    Optional<Department> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
